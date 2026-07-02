package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.EvaluacionInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.EvaluacionDTO;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface EvaluacionResponseMapper {
    @Mapping(target = "fechaInicio", source = "fechaInicio", qualifiedByName = "toInstant")
    @Mapping(target = "fechaFin", source = "fechaFin", qualifiedByName = "toInstant")
    EvaluacionDTO toResponse(EvaluacionInfo evaluacionInfo);

    @Named("toInstant")
    default Instant toInstant(LocalDateTime dateTime){
        return dateTime.atZone(ZoneId.of("America/Asuncion")).toInstant();
    }
}
