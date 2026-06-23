package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.info;

import java.time.LocalDateTime;
import java.util.List;

public record FormularioInfo(
        Integer id,
        String descripcion,
        LocalDateTime fechaApertura,
        LocalDateTime fechaCierre,
        boolean activo,
        List<IndicadorInfo> indicadores
) {
}
