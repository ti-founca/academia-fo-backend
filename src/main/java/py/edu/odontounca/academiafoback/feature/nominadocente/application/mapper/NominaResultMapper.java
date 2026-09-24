package py.edu.odontounca.academiafoback.feature.nominadocente.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.dto.info.NominaInfo;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.model.Nomina;
import py.edu.odontounca.academiafoback.shared.pagination.Page;

@Mapper(componentModel = "spring")
public interface NominaResultMapper {
    NominaInfo toInfo(Nomina nomina);
    default Page<NominaInfo> toPageInfo(Page<Nomina> page){
        return Page.of(
            page.content().stream().map(this::toInfo).toList(),
            page.page(),
            page.size(),
            page.totalElements()
        );
    };
}
