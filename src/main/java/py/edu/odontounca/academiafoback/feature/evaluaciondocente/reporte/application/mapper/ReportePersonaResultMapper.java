package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PersonaInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.BuscarReportePersonaPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Persona;

@Mapper(componentModel = "spring")
public interface ReportePersonaResultMapper {
    PersonaInfo toInfo(Persona persona);
    BuscarReportePersonaPorIdUseCase.Result toResult(Persona persona);
}
