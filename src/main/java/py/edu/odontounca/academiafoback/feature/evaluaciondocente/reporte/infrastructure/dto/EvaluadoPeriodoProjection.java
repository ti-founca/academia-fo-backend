package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.dto;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.MateriaJPAModel;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.PersonaJPAModel;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.TipoDocenteJPAModel;

public record EvaluadoPeriodoProjection(
        PersonaJPAModel evaluado,
        MateriaJPAModel materia,
        TipoDocenteJPAModel tipoDocente
) { }
