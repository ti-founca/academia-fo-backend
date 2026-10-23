package py.edu.odontounca.academiafoback.feature.autenticacion.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.autenticacion.domain.model.RefreshToken;
import py.edu.odontounca.academiafoback.feature.autenticacion.domain.repository.RefreshTokenRepository;
import py.edu.odontounca.academiafoback.feature.autenticacion.infrastructure.jpa.mapper.RefreshTokenJPAMapper;
import py.edu.odontounca.academiafoback.feature.autenticacion.infrastructure.jpa.repository.RefreshTokenJPARepository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
    private final RefreshTokenJPARepository jpaRepository;
    private final RefreshTokenJPAMapper mapper;

    @Override
    public RefreshToken guardar(RefreshToken refreshToken) {
        return this.mapper.toDomain(this.jpaRepository.save(this.mapper.toJPA(refreshToken)));
    }

    @Override
    public Optional<RefreshToken> buscarPorJti(UUID jti) {
        return this.jpaRepository.findByJti(jti).map(this.mapper::toDomain);
    }
}
