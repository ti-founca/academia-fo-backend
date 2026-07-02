package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.EstamentoInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Estamento;

@Mapper(componentModel = "spring")
public interface EstamentoResultMapper {
    EstamentoInfo toInfo(Estamento estamento);
}
