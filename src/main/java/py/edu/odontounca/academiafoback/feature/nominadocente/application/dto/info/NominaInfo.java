package py.edu.odontounca.academiafoback.feature.nominadocente.application.dto.info;

import java.util.List;

public record NominaInfo(
        Integer id,
        String nombre,
        PeriodoLectivoInfo periodoLectivo,
        PlanCurricularInfo planCurricular,
        List<NominaDetalleInfo> detalles
) {
}
