package py.edu.odontounca.academiafoback.feature.estamento.presentation.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.estamento.application.dto.info.EstamentoInfo;
import py.edu.odontounca.academiafoback.feature.estamento.presentation.dto.EstamentoDTO;
import py.edu.odontounca.academiafoback.shared.pagination.Page;

@Mapper(componentModel = "spring")
public interface EstamentoResponseMapper {
    EstamentoDTO toResponse(EstamentoInfo estamentoInfo);
    default Page<EstamentoDTO> toResponsePage(Page<EstamentoInfo> pageInfo){
        return Page.of(
            pageInfo.content().stream().map(this::toResponse).toList(),
            pageInfo.page(),
            pageInfo.size(),
            pageInfo.totalElements()
        );
    }
}
