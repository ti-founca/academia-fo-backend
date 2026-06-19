package py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.repository;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.DetalleEvaluacion;

import java.util.Optional;

public interface DetalleEvaluacionRepository {
    Optional<DetalleEvaluacion> buscarPorId(Integer id);
}
