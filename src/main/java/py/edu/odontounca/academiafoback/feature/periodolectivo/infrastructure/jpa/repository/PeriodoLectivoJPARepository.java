package py.edu.odontounca.academiafoback.feature.periodolectivo.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import py.edu.odontounca.academiafoback.feature.periodolectivo.infrastructure.jpa.model.PeriodoLectivoJPAModel;

public interface PeriodoLectivoJPARepository extends JpaRepository<PeriodoLectivoJPAModel, Integer>, JpaSpecificationExecutor<PeriodoLectivoJPAModel> {
}
