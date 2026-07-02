package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Estamento;

import java.util.List;
import java.util.Optional;

public interface EstamentoRepository {
    List<Estamento> consultarTodos();
    Optional<Estamento> buscarPorId(Integer id);
}
