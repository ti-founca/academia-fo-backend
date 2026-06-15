package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.model.MateriaJPAModel;

public interface MateriaJPARepository extends JpaRepository<MateriaJPAModel, Integer> {
}
