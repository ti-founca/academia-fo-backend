package py.edu.odontounca.academiafoback.feature.autenticacion.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.autenticacion.application.dto.info.UsuarioAutenticadoInfo;
import py.edu.odontounca.academiafoback.feature.autenticacion.domain.model.Usuario;

@Mapper(componentModel = "spring")
public interface AutenticacionResultMapper {
    UsuarioAutenticadoInfo toInfo(Usuario usuario);
}
