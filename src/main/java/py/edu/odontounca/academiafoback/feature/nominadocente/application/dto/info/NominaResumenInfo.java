package py.edu.odontounca.academiafoback.feature.nominadocente.application.dto.info;

public record NominaResumenInfo(
        Integer id,
        String nombre,
        PeriodoLectivoInfo periodoLectivo,
        PlanCurricularInfo planCurricular
) {
}
