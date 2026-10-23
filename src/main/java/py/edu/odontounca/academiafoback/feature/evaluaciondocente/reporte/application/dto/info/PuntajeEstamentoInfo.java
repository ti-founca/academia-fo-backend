package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info;

import java.math.BigDecimal;
import java.util.List;

public record PuntajeEstamentoInfo(
        EstamentoInfo estamento,
        BigDecimal puntaje,
        BigDecimal puntajePonderado,
        List<PuntajeIndicadorInfo> puntajesPorIndicador
) { }
