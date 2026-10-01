package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.dto;

public record NominaResumenProjection(
        Integer id,
        String nombre,
        Integer periodoLectivoId,
        String periodoLectivoDescripcion,
        Integer planCurricularId,
        String planCurricularDescripcion
) { }
