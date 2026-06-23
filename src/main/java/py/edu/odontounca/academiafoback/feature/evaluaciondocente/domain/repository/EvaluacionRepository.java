package py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.repository;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Evaluacion;

import java.util.Optional;

public interface EvaluacionRepository {
    Optional<Evaluacion> buscarPorId(Integer id);
}
