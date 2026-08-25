package py.edu.odontounca.academiafoback.feature.curso.domain.repository;

import py.edu.odontounca.academiafoback.feature.curso.domain.model.Curso;
import py.edu.odontounca.academiafoback.shared.filter.Filter;
import py.edu.odontounca.academiafoback.shared.pagination.Page;
import py.edu.odontounca.academiafoback.shared.pagination.PageRequest;

public interface CursoRepository {
    Page<Curso> consultarTodos(Filter filter, PageRequest pageRequest);
}
