package py.edu.odontounca.academiafoback.feature.autenticacion.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.dto.info.TokensInfo;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.dto.info.UsuarioAutenticadoInfo;
import py.edu.odontounca.academiafoback.feature.autenticacion.presentation.dto.TokensDTO;
import py.edu.odontounca.academiafoback.feature.autenticacion.presentation.dto.UsuarioAutenticadoDTO;

@Mapper(componentModel = "spring")
public interface AutenticacionResponseMapper {
    @Mapping(target = "tipo", constant = "Bearer")
    TokensDTO toResponse(TokensInfo tokensInfo);

    UsuarioAutenticadoDTO toResponse(UsuarioAutenticadoInfo usuarioInfo);
}
