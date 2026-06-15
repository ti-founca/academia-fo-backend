package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.result.IndicadorResult;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Indicador;

@Mapper(componentModel = "spring")
public interface IndicadorResultMapper {
    IndicadorResult toResult(Indicador indicador);
}
