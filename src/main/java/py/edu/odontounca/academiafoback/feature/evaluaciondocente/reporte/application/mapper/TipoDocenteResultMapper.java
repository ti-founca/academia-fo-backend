package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.TipoDocenteInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.TipoDocente;

@Mapper(componentModel = "spring")
public interface TipoDocenteResultMapper {
    TipoDocenteInfo toInfo(TipoDocente tipoDocente);
}
