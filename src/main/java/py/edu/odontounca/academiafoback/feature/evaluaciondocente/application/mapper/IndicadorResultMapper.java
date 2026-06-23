package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.info.IndicadorInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.usecase.BuscarIndicadorPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Indicador;

@Mapper(componentModel = "spring")
public interface IndicadorResultMapper {
    BuscarIndicadorPorIdUseCase.Result toResult(Indicador indicador);
    IndicadorInfo toInfo(Indicador indicador);
}
