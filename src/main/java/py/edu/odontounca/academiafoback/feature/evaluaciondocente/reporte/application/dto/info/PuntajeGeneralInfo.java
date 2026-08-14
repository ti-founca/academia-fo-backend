package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info;

import java.math.BigDecimal;
import java.util.List;

public record PuntajeGeneralInfo(
        PersonaInfo evaluado,
        MateriaInfo materia,
        TipoDocenteInfo tipoDocente,
        BigDecimal promedioGeneral,
        List<PuntajeGeneralDetalleInfo> detalles
) {
}
