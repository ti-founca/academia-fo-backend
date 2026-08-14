package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info;

import java.math.BigDecimal;

public record PuntajeGeneralDetalleInfo(
        EstamentoInfo estamento,
        BigDecimal promedio,
        BigDecimal promedioPonderado
) { }
