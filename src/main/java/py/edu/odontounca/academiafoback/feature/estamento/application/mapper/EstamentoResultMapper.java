package py.edu.odontounca.academiafoback.feature.estamento.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.estamento.application.dto.info.EstamentoInfo;
import py.edu.odontounca.academiafoback.feature.estamento.domain.model.Estamento;
import py.edu.odontounca.academiafoback.shared.pagination.Page;

@Mapper(componentModel = "spring")
public interface EstamentoResultMapper {
    EstamentoInfo toInfo(Estamento estamento);
    default Page<EstamentoInfo> toPageInfo(Page<Estamento> page){
        return Page.of(
            page.content().stream().map(this::toInfo).toList(),
            page.page(),
            page.size(),
            page.totalElements()
        );
    }
}
