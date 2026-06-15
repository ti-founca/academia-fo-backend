package py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.result.IndicadorResult;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.dto.IndicadorDTO;

@Mapper(componentModel = "spring")
public interface IndicadorResponseMapper {
    IndicadorDTO toResponse(IndicadorResult result);
}
