package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.info.PersonaInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.usecase.BuscarPersonaPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Persona;

@Mapper(componentModel = "spring")
public interface PersonaResultMapper {
    PersonaInfo toInfo(Persona persona);
    BuscarPersonaPorIdUseCase.Result toResult(Persona persona);
}
