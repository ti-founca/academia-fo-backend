package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.TipoDocenteInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.TipoDocenteDTO;

@Mapper(componentModel = "spring")
public interface TipoDocenteResponseMapper {
    TipoDocenteDTO toResponse(TipoDocenteInfo tipoDocenteInfo);
}
