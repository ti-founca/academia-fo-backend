package py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.info.PersonaInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.dto.PersonaDTO;

@Mapper(componentModel = "spring")
public interface PersonaResponseMapper {
    PersonaDTO toResponse(PersonaInfo personaInfo);
}
