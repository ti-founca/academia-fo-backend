package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.dto;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.EstamentoJPAModel;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.IndicadorJPAModel;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.TipoDocenteJPAModel;

public record PuntajeIndicadorProjection(
    IndicadorJPAModel indicador,
    TipoDocenteJPAModel tipoDocente,
    EstamentoJPAModel estamento,
    Double promedio,
    Long cantidad
) { }
