package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.EstamentoInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.EstamentoDTO;

@Mapper(componentModel = "spring")
public interface EstamentoResponseMapper {
    EstamentoDTO toResponse(EstamentoInfo estamentoInfo);
}
