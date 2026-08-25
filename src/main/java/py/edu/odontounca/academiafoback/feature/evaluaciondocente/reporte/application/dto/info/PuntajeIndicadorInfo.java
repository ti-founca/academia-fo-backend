package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.TipoDocente;

import java.math.BigDecimal;

public record PuntajeIndicadorInfo(
        EstamentoInfo estamento,
        TipoDocenteInfo tipoDocente,
        IndicadorInfo indicador,
        BigDecimal puntaje,
        Integer cantidad
) { }
