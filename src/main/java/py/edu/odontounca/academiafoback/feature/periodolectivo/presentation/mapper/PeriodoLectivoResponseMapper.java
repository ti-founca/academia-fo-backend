package py.edu.odontounca.academiafoback.feature.periodolectivo.presentation.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.periodolectivo.application.dto.info.PeriodoLectivoInfo;
import py.edu.odontounca.academiafoback.feature.periodolectivo.domain.model.PeriodoLectivo;
import py.edu.odontounca.academiafoback.feature.periodolectivo.presentation.dto.PeriodoLectivoDTO;
import py.edu.odontounca.academiafoback.shared.pagination.Page;

@Mapper(componentModel = "spring")
public interface PeriodoLectivoResponseMapper {
    PeriodoLectivoDTO toResponse(PeriodoLectivoInfo periodoLectivoInfo);
    default Page<PeriodoLectivoDTO> toResponsePage(Page<PeriodoLectivoInfo> pageInfo){
        return Page.of(
            pageInfo.content().stream().map(this::toResponse).toList(),
            pageInfo.page(),
            pageInfo.size(),
            pageInfo.totalElements()
        );
    };
}
