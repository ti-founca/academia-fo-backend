package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.repository;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.dto.PuntajeIndicadorProjection;

import java.util.List;

public interface PuntajePorIndicadorJPARepository {
    public List<PuntajeIndicadorProjection> obtenerPuntajeIndicador(Integer idPeriodo, Integer idEstamento, Integer idTipoDocente);
}
