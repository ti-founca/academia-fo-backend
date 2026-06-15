package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.model.IndicadorJPAModel;

public interface IndicadorJPARepository extends JpaRepository<IndicadorJPAModel, Integer> {
}
