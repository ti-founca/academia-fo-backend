package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.EvaluacionJPAModel;

public interface EvaluacionJPARepository extends JpaRepository<EvaluacionJPAModel, Integer> {
}
