package py.edu.odontounca.academiafoback.feature.nominadocente.presentation.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.dto.info.NominaResumenInfo;
import py.edu.odontounca.academiafoback.feature.nominadocente.presentation.dto.NominaResumenDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NominaResumenResponseMapper {
    NominaResumenDTO toResponse(NominaResumenInfo nominaResumenInfo);
    List<NominaResumenDTO> toResponseList(List<NominaResumenInfo> nominasResumenInfo);
}
