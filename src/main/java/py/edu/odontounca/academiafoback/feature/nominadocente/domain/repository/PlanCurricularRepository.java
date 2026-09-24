package py.edu.odontounca.academiafoback.feature.nominadocente.domain.repository;

import py.edu.odontounca.academiafoback.feature.nominadocente.domain.model.PlanCurricular;

import java.util.Optional;

public interface PlanCurricularRepository {
    Optional<PlanCurricular> findById(Integer id);
}
