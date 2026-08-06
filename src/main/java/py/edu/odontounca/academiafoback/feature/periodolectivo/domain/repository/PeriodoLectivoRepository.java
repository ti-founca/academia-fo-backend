package py.edu.odontounca.academiafoback.feature.periodolectivo.domain.repository;

import py.edu.odontounca.academiafoback.feature.periodolectivo.domain.model.PeriodoLectivo;
import py.edu.odontounca.academiafoback.shared.filter.Filter;
import py.edu.odontounca.academiafoback.shared.pagination.Page;
import py.edu.odontounca.academiafoback.shared.pagination.PageRequest;

import java.util.Optional;

public interface PeriodoLectivoRepository {
    Page<PeriodoLectivo> findByFilter(Filter filter, PageRequest pageRequest);
    Optional<PeriodoLectivo> findById(Integer id);
}
