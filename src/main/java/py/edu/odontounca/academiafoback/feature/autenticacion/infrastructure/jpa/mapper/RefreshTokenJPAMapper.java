package py.edu.odontounca.academiafoback.feature.autenticacion.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.autenticacion.domain.model.RefreshToken;
import py.edu.odontounca.academiafoback.feature.autenticacion.infrastructure.jpa.model.RefreshTokenJPAModel;

@Mapper(componentModel = "spring")
public interface RefreshTokenJPAMapper {
    default RefreshToken toDomain(RefreshTokenJPAModel refreshTokenJpa){
        return RefreshToken.reconstruir(
                refreshTokenJpa.getId(),
                refreshTokenJpa.getJti(),
                refreshTokenJpa.getIdUsuario(),
                refreshTokenJpa.getEmitidoEn(),
                refreshTokenJpa.getExpiraEn(),
                refreshTokenJpa.getRevocadoEn()
        );
    }

    default RefreshTokenJPAModel toJPA(RefreshToken refreshToken){
        RefreshTokenJPAModel refreshTokenJpa = new RefreshTokenJPAModel();
        refreshTokenJpa.setId(refreshToken.getId());
        refreshTokenJpa.setJti(refreshToken.getJti());
        refreshTokenJpa.setIdUsuario(refreshToken.getIdUsuario());
        refreshTokenJpa.setEmitidoEn(refreshToken.getEmitidoEn());
        refreshTokenJpa.setExpiraEn(refreshToken.getExpiraEn());
        refreshTokenJpa.setRevocadoEn(refreshToken.getRevocadoEn());
        return refreshTokenJpa;
    }
}
