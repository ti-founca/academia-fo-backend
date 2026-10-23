package py.edu.odontounca.academiafoback.feature.autenticacion.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.autenticacion.domain.model.Usuario;
import py.edu.odontounca.academiafoback.feature.autenticacion.infrastructure.jpa.model.UsuarioJPAModel;

@Mapper(componentModel = "spring")
public interface UsuarioJPAMapper {
    default Usuario toDomain(UsuarioJPAModel usuarioJpa){
        return Usuario.reconstruir(
                usuarioJpa.getId(),
                usuarioJpa.getNombre(),
                usuarioJpa.getApellido(),
                usuarioJpa.getCorreo(),
                usuarioJpa.getClaveCifrada()
        );
    }
}
