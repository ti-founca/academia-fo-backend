package py.edu.odontounca.academiafoback.feature.curso.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import py.edu.odontounca.academiafoback.feature.curso.infrastructure.jpa.model.CursoJPAModel;

public interface CursoJPARepository extends JpaRepository<CursoJPAModel, Integer>, JpaSpecificationExecutor<CursoJPAModel> {
}
