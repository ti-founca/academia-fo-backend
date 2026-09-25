package py.edu.odontounca.academiafoback.feature.nominadocente.presentation.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.dto.info.DocenteInfo;
import py.edu.odontounca.academiafoback.feature.nominadocente.presentation.dto.DocenteDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocenteResponseMapper {
    DocenteDTO toResponse(DocenteInfo docenteInfo);
    List<DocenteDTO> toResponseList(List<DocenteInfo> docentesInfo);
}
