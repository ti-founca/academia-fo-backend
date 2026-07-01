package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.model.TipoDocenteJPAModel;

public interface TipoDocenteJPARepository extends JpaRepository<TipoDocenteJPAModel, Integer> {
}
