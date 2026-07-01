package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.info;

import java.math.BigDecimal;

public record DetalleEvaluacionGeneralDocenteMateriaInfo(
        EstamentoInfo estamento,
        BigDecimal promedio,
        BigDecimal promedioPonderado
) { }
