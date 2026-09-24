package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.model.PlanCurricularJPAModel;

public interface PlanCurricularJPARepository extends JpaRepository<PlanCurricularJPAModel, Integer> {
}
