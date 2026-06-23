package py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.info.FormularioInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.dto.FormularioDTO;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface FormularioResponseMapper {
    @Mapping(target = "fechaApertura", source = "fechaApertura", qualifiedByName = "toInstant")
    @Mapping(target = "fechaCierre", source = "fechaCierre", qualifiedByName = "toInstant")
    FormularioDTO toResponse(FormularioInfo formularioInfo);

    @Named("toInstant")
    default Instant toInstant(LocalDateTime dateTime){
        return dateTime.atZone(ZoneId.of("America/Asuncion")).toInstant();
    }
}
