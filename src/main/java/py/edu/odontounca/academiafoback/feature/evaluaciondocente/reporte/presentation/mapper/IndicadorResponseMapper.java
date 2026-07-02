package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.IndicadorInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.IndicadorDTO;

@Mapper(componentModel = "spring")
public interface IndicadorResponseMapper {
    IndicadorDTO toResponse(IndicadorInfo result);
}
