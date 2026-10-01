package py.edu.odontounca.academiafoback.feature.nominadocente.domain.repository;

import py.edu.odontounca.academiafoback.feature.nominadocente.domain.model.PeriodoLectivo;

import java.util.Optional;

public interface PeriodoLectivoRepository {
    Optional<PeriodoLectivo> findById(Integer id);
    boolean existsById(Integer id);
}
