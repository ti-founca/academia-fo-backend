package py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.repository;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.TipoDocente;

import java.util.List;
import java.util.Optional;

public interface TipoDocenteRepository {
    List<TipoDocente> consultarTodos();
    Optional<TipoDocente> buscarPorId(Integer id);
}
