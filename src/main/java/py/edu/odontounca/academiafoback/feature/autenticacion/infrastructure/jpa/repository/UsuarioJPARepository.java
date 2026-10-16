package py.edu.odontounca.academiafoback.feature.autenticacion.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.edu.odontounca.academiafoback.feature.autenticacion.infrastructure.jpa.model.UsuarioJPAModel;

import java.util.List;

public interface UsuarioJPARepository extends JpaRepository<UsuarioJPAModel, Integer> {
    List<UsuarioJPAModel> findAllByCorreoIgnoreCaseOrderByIdAsc(String correo);
}
