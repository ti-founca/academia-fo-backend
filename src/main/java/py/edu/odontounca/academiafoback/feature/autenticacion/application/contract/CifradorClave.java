package py.edu.odontounca.academiafoback.feature.autenticacion.application.contract;

public interface CifradorClave {
    /**
     * Compara la clave en texto plano con su hash. Si {@code claveCifrada} es nulo
     * igual realiza una comparación para no revelar por tiempo de respuesta si el usuario existe.
     */
    boolean coincide(String clavePlana, String claveCifrada);
}
