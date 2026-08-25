package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PuntajeIndicadorInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.PuntajesPorIndicadorDTO;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PuntajePorIndicadorResponseMapper {
    @Mapping(source = "puntaje", target = "promedio", qualifiedByName = "bigDecimalToDouble")
    PuntajesPorIndicadorDTO toResponse(PuntajeIndicadorInfo puntajeIndicadorInfo);

    List<PuntajesPorIndicadorDTO> toResponseList(List<PuntajeIndicadorInfo> puntajeIndicadorInfo);

    @Named("bigDecimalToDouble")
    default double bigDecimalToDouble(BigDecimal numero){
        return numero.doubleValue();
    }
}
