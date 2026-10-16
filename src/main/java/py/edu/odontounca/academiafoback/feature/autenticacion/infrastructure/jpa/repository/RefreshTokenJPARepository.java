package py.edu.odontounca.academiafoback.feature.autenticacion.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.edu.odontounca.academiafoback.feature.autenticacion.infrastructure.jpa.model.RefreshTokenJPAModel;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenJPARepository extends JpaRepository<RefreshTokenJPAModel, Integer> {
    Optional<RefreshTokenJPAModel> findByJti(UUID jti);
}
