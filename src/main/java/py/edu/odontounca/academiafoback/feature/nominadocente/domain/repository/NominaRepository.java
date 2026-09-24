package py.edu.odontounca.academiafoback.feature.nominadocente.domain.repository;

import py.edu.odontounca.academiafoback.feature.nominadocente.domain.model.Nomina;
import py.edu.odontounca.academiafoback.shared.filter.Filter;
import py.edu.odontounca.academiafoback.shared.pagination.Page;
import py.edu.odontounca.academiafoback.shared.pagination.PageRequest;

import java.util.Optional;

public interface NominaRepository {
    Page<Nomina> findByFilter(Filter filter, PageRequest pageRequest);
    Optional<Nomina> findById(Integer id);
}
