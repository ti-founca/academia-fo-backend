package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.EvaluacionDetalleInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.BuscarDetalleEvaluacionPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.DetalleEvaluacion;

@Mapper(componentModel = "spring")
public interface DetalleEvaluacionResultMapper {
    BuscarDetalleEvaluacionPorIdUseCase.Result toResult(DetalleEvaluacion detalleEvaluacion);
    EvaluacionDetalleInfo toInfo(DetalleEvaluacion detalleEvaluacion);
}
