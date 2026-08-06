package py.edu.odontounca.academiafoback.feature.periodolectivo.application;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.periodolectivo.application.dto.info.PeriodoLectivoInfo;
import py.edu.odontounca.academiafoback.feature.periodolectivo.domain.model.PeriodoLectivo;
import py.edu.odontounca.academiafoback.shared.pagination.Page;

@Mapper(componentModel = "spring")
public interface PeriodoLectivoResultMapper {
    PeriodoLectivoInfo toInfo(PeriodoLectivo periodoLectivo);
    default Page<PeriodoLectivoInfo> toPageInfo(Page<PeriodoLectivo> page){
        return Page.of(
            page.content().stream().map(this::toInfo).toList(),
            page.page(),
            page.size(),
            page.totalElements()
        );
    };
}
