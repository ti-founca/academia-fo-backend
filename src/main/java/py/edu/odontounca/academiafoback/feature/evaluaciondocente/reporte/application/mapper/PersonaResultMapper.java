package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PersonaInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.BuscarPersonaPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Persona;

@Mapper(componentModel = "spring")
public interface PersonaResultMapper {
    PersonaInfo toInfo(Persona persona);
    BuscarPersonaPorIdUseCase.Result toResult(Persona persona);
}
