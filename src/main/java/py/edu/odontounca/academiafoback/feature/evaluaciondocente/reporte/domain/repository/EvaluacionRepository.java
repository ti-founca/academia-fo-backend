package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Evaluacion;

import java.util.Optional;

public interface EvaluacionRepository {
    Optional<Evaluacion> buscarPorId(Integer id);
}
