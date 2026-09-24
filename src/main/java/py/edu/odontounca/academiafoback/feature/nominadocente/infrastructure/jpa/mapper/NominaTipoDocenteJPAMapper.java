package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.model.TipoDocenteJPAModel;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.model.TipoDocente;

@Mapper(componentModel = "spring")
public interface NominaTipoDocenteJPAMapper {
    default TipoDocente toDomain(TipoDocenteJPAModel tipoDocenteJpa){
        return TipoDocente.reconstruir(
                tipoDocenteJpa.getId(),
                tipoDocenteJpa.getNombre()
        );
    };
}
