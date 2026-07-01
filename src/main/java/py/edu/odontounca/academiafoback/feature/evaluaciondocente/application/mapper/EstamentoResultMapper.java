package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.info.EstamentoInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Estamento;

@Mapper(componentModel = "spring")
public interface EstamentoResultMapper {
    EstamentoInfo toInfo(Estamento estamento);
}
