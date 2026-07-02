package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.DetalleEvaluacionJPAModel;

public interface DetalleEvaluacionJPARepository extends JpaRepository<DetalleEvaluacionJPAModel, Integer> {
}
