package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.EvaluacionInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.BuscarEvaluacionPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Evaluacion;

@Mapper(componentModel = "spring")
public interface EvaluacionResultMapper {
    EvaluacionInfo toInfo(Evaluacion evaluacion);
    BuscarEvaluacionPorIdUseCase.Result toResult(Evaluacion evaluacion);
}
