package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.info.TipoDocenteInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.TipoDocente;

@Mapper(componentModel = "spring")
public interface TipoDocenteResultMapper {
    TipoDocenteInfo toInfo(TipoDocente tipoDocente);
}
