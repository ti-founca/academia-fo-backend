package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.TipoDocente;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.model.TipoDocenteJPAModel;

@Mapper(componentModel = "spring")
public interface TipoDocenteJPAMapper {
    default TipoDocente toDomain(TipoDocenteJPAModel tipoDocenteJpa){
        return TipoDocente.reconstruir(tipoDocenteJpa.getId(), tipoDocenteJpa.getNombre());
    }
}
