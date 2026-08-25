package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.TipoDocenteInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.TipoDocente;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.TipoDocenteJPAModel;

@Mapper(componentModel = "spring")
public interface TipoDocenteJPAMapper {
    default TipoDocente toDomain(TipoDocenteJPAModel tipoDocenteJpa){
        return TipoDocente.reconstruir(tipoDocenteJpa.getId(), tipoDocenteJpa.getNombre());
    }

    @Mapping(source = "nombre", target = "descripcion")
    TipoDocenteInfo toInfo(TipoDocenteJPAModel tipoDocenteJpa);
}
