package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.info.DetalleEvaluacionInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.usecase.BuscarDetalleEvaluacionPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.DetalleEvaluacion;

@Mapper(componentModel = "spring")
public interface DetalleEvaluacionResultMapper {
    BuscarDetalleEvaluacionPorIdUseCase.Result toResult(DetalleEvaluacion detalleEvaluacion);
    DetalleEvaluacionInfo toInfo(DetalleEvaluacion detalleEvaluacion);
}
