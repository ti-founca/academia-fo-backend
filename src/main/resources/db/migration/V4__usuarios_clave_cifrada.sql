-- pgcrypto genera hashes bcrypt ($2a$) compatibles con BCryptPasswordEncoder de Spring
CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;

ALTER TABLE IF EXISTS config.usuarios
    ADD COLUMN IF NOT EXISTS clave_cifrada character varying(60);

UPDATE config.usuarios
SET clave_cifrada = public.crypt(clave, public.gen_salt('bf', 10))
WHERE clave IS NOT NULL
  AND clave <> ''
  AND clave_cifrada IS NULL;

-- Mientras el sistema legado siga escribiendo la clave en texto plano, se recalcula el hash
CREATE OR REPLACE FUNCTION config.fn_usuarios_cifrar_clave()
    RETURNS trigger
    LANGUAGE plpgsql
AS $$
BEGIN
    IF TG_OP = 'UPDATE' AND NEW.clave IS NOT DISTINCT FROM OLD.clave THEN
        RETURN NEW;
    END IF;

    IF NEW.clave IS NOT NULL AND NEW.clave <> '' THEN
        NEW.clave_cifrada := public.crypt(NEW.clave, public.gen_salt('bf', 10));
    ELSIF TG_OP = 'UPDATE' THEN
        NEW.clave_cifrada := NULL;
    END IF;

    RETURN NEW;
END;
$$;

DROP TRIGGER IF EXISTS tg_usuarios_cifrar_clave ON config.usuarios;
CREATE TRIGGER tg_usuarios_cifrar_clave
    BEFORE INSERT OR UPDATE OF clave
    ON config.usuarios
    FOR EACH ROW
EXECUTE FUNCTION config.fn_usuarios_cifrar_clave();
