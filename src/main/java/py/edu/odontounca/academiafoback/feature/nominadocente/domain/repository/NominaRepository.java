package py.edu.odontounca.academiafoback.feature.nominadocente.domain.repository;

import py.edu.odontounca.academiafoback.feature.nominadocente.domain.model.Nomina;

import java.util.Optional;

public interface NominaRepository {
    Optional<Nomina> findById(Integer id);
}
