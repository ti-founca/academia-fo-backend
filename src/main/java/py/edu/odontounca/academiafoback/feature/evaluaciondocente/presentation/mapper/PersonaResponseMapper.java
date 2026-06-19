package py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.result.PersonaResult;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.dto.PersonaDTO;

@Mapper(componentModel = "spring")
public interface PersonaResponseMapper {
    PersonaDTO toResponse(PersonaResult personaResult);
}
