package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.result.PersonaResult;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Persona;

@Mapper(componentModel = "spring")
public interface PersonaResultMapper {
    PersonaResult toResult(Persona persona);
}
