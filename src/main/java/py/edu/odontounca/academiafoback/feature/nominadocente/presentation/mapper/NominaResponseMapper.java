package py.edu.odontounca.academiafoback.feature.nominadocente.presentation.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.dto.info.NominaInfo;
import py.edu.odontounca.academiafoback.feature.nominadocente.presentation.dto.NominaDTO;
import py.edu.odontounca.academiafoback.shared.pagination.Page;

@Mapper(componentModel = "spring")
public interface NominaResponseMapper {
    NominaDTO toResponse(NominaInfo nominaInfo);
    default Page<NominaDTO> toResponsePage(Page<NominaInfo> pageInfo){
        return Page.of(
            pageInfo.content().stream().map(this::toResponse).toList(),
            pageInfo.page(),
            pageInfo.size(),
            pageInfo.totalElements()
        );
    };
}
