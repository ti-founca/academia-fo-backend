package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info;

import java.math.BigDecimal;
import java.util.List;

public record HojaPuntajesIndicadorInfo(
        EstamentoInfo estamento,
        TipoDocenteInfo tipoDocente,
        List<PuntajeIndicadorInfo> indicadores,
        BigDecimal promedio
) { }
