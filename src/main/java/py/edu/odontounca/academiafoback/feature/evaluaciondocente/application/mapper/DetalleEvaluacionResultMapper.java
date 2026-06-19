package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.result.DetalleEvaluacionResult;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.DetalleEvaluacion;

@Mapper(componentModel = "spring")
public interface DetalleEvaluacionResultMapper {
    DetalleEvaluacionResult toResult(DetalleEvaluacion detalleEvaluacion);
}
