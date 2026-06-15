package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.result;

import java.time.LocalDateTime;
import java.util.List;

public record FormularioResult(
        Integer id,
        String descripcion,
        LocalDateTime fechaApertura,
        LocalDateTime fechaCierre,
        boolean activo,
        List<IndicadorResult> indicadores
) {
}
