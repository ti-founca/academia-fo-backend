package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PersonaInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.PersonaDTO;

@Mapper(componentModel = "spring")
public interface PersonaResponseMapper {
    PersonaDTO toResponse(PersonaInfo personaInfo);
}
