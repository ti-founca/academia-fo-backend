package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.repository.query;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.dto.PuntajeIndicadorProjection;

import java.util.List;

public interface PuntajePorIndicadorJPAQuery {
    public List<PuntajeIndicadorProjection> obtenerPuntajeIndicador(Integer idPeriodo, Integer idEstamento, Integer idTipoDocente, Integer idEvaluado, Integer idMateria);
}
