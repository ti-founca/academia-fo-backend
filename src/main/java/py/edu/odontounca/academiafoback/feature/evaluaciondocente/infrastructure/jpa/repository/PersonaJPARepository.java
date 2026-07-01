package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.model.PersonaJPAModel;

import java.util.List;

public interface PersonaJPARepository extends JpaRepository<PersonaJPAModel, Integer> {
}
