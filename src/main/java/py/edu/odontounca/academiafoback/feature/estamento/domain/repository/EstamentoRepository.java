package py.edu.odontounca.academiafoback.feature.estamento.domain.repository;

import py.edu.odontounca.academiafoback.feature.estamento.domain.model.Estamento;
import py.edu.odontounca.academiafoback.shared.filter.Filter;
import py.edu.odontounca.academiafoback.shared.pagination.Page;
import py.edu.odontounca.academiafoback.shared.pagination.PageRequest;

public interface EstamentoRepository {
    Page<Estamento> findByFilter(Filter filter, PageRequest pageRequest);
}
