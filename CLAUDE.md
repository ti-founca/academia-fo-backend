# CLAUDE.md

Backend de **AcademiaFO** (Facultad de Odontología – UNCA). API REST sobre una base PostgreSQL **ya existente (legada)**, usada principalmente para reportes de evaluación docente.

## Stack

- Java 21, Spring Boot 4 (webmvc, data-jpa), Gradle (wrapper), PostgreSQL
- Flyway para migraciones, Lombok, MapStruct, Apache POI (exportación Excel)
- Versiones de libs externas en `gradle/libs.versions.toml` (`libs.*` en `build.gradle`)

## Comandos

```bash
./gradlew bootRun      # levantar (puerto 8080)
./gradlew build        # compilar + tests
./gradlew test         # tests (solo existe contextLoads, requiere DB)
./gradlew compileJava  # verificación rápida (MapStruct/Lombok generan código aquí)
```

Variables de entorno requeridas: `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD`, `JWT_SECRET` (≥ 32 bytes; p. ej. `openssl rand -base64 48`).

## Base de datos

- El esquema **no lo maneja Hibernate** (`ddl-auto=none`). Las tablas ya existen; los `*JPAModel` se mapean a ellas con `@Table(schema=..., name=...)` y `@Column(name=...)` explícitos (nombres legados, p. ej. `pel_descri`, `pel_anho`).
- Esquemas usados: `public` (personas, materias, cursos, periodoslectivos, plan curricular), `academico` (nomina_docente, tipo_docente), `evaluacion` (evaluacion, evaluacion_detalle, formulario, indicador, estamento, tipo_evaluacion).
- Flyway: `src/main/resources/db/migration/V{n}__descripcion.sql`, `baseline-on-migrate=true`, tabla de historial en el esquema `config`. Solo agregar migraciones nuevas; nunca editar las ya aplicadas.

## Arquitectura

Clean/hexagonal por feature: `src/main/java/py/edu/odontounca/academiafoback/`

```
feature/<feature>/
  domain/          model/ (entidades puras, vo/ enums), repository/ (interfaces/puertos)
  application/     usecase/, service/, dto/{info,query,result}/, mapper/ (Domain -> Info), contract/ (puertos no-dominio: queries, Excel)
  infrastructure/  jpa/{model,repository,mapper}/, repository/ (*RepositoryImpl), adapter/, dto/ (proyecciones)
  presentation/    controller/, dto/ (*DTO records), mapper/ (Info -> DTO)
shared/            pagination, filter, exception, validation, spring (adaptadores a Spring), config (CORS)
```

`periodolectivo` es el feature de referencia más limpio para copiar el patrón.

Flujo: `Controller` → `UseCase.execute(Query)` → `Repository` (interfaz de dominio) → `RepositoryImpl` → `JPARepository` → `JPAMapper.toDomain` → `ResultMapper.toInfo` → `ResponseMapper.toResponse` → `DTO`.

## Convenciones

- **Nombres en español** (clases, métodos, mensajes): `BuscarXPorIdUseCase`, `ConsultarTodosXUseCase`, `ObtenerX...UseCase`, métodos de repo `buscarPorId` / `findByFilter`.
- **Use cases**: `@Service` + `@RequiredArgsConstructor`, un método público `execute(Query)` que devuelve `Result`; `Query` y `Result` son `record`s anidados en la propia clase.
- **Dominio**: sin anotaciones de Spring/JPA/Lombok. Constructor valida con `shared.validation.Assert` (mensajes tipo `"Entidad: «campo» ..."`); fábricas estáticas `crear(...)` (sin id) y `reconstruir(...)` (con id, usada desde la infraestructura). Getters escritos a mano.
- **VOs/enums** con código de BD: `fromCode(...)` que lanza `IllegalArgumentException` si es desconocido.
- **JPA models**: sufijo `JPAModel`, Lombok `@Getter @Setter`. Repos Spring: sufijo `JPARepository`; extender `JpaSpecificationExecutor` si se filtra.
- **Mappers**: MapStruct `@Mapper(componentModel = "spring")`; los JPA→dominio se escriben como método `default` llamando a `reconstruir`. Tres capas de mappers: `*JPAMapper`, `*ResultMapper`, `*ResponseMapper`.
- **DTOs**: `record`s. `*Info` en application, `*DTO` en presentation. El dominio nunca sale del use case.
- **Rutas**: `/api/<recurso-en-plural-kebab>` (p. ej. `/api/periodos-lectivos`, `/api/evaluaciones/reportes/...`).
- **Errores**: lanzar `NotFoundError(recurso, id)` (subclase de `ApplicationError`); `GlobalExceptionHandler` lo traduce a 404 + `ErrorResponse`.
- **Paginación**: usar `shared.pagination.{Page, PageRequest, Sort}` (no los de Spring fuera de infraestructura). La API es **base 1**; `PaginationConverter` convierte a/desde Spring (base 0). Sort por query string: `sort=+campo,-otro`.
- **Filtros**: `shared.filter.Filter` (`Filter.of(new Criteria(...))`) + `GenericSpecificationBuilder` con un `fieldMapping` (campo API → campo JPA) en el `RepositoryImpl`.
- Consultas de agregación/reportes: `@Query` JPQL en `*QueryJPARepository` o implementaciones custom (`*JPARepositoryImpl`) que devuelven proyecciones de `infrastructure/dto`, expuestas a application mediante una interfaz en `application/contract`.

## Git

- Commits en español con Conventional Commits: `feat: ...`, `fix: ...`, `refactor: ...`, `chore: ...`.
- Rama principal: `master`; ramas `feat/<nombre>`.
