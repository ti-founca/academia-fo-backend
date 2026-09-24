package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.model.NominaJPAModel;

public interface NominaJPARepository extends JpaRepository<NominaJPAModel, Integer>, JpaSpecificationExecutor<NominaJPAModel> {
}
