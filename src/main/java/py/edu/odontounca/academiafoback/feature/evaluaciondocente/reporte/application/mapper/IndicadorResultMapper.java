package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.IndicadorInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.BuscarIndicadorPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Indicador;

@Mapper(componentModel = "spring")
public interface IndicadorResultMapper {
    BuscarIndicadorPorIdUseCase.Result toResult(Indicador indicador);
    IndicadorInfo toInfo(Indicador indicador);
}
