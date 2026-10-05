package py.edu.odontounca.academiafoback.feature.nominadocente.presentation.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.dto.info.MateriaInfo;
import py.edu.odontounca.academiafoback.feature.nominadocente.presentation.dto.MateriaDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NominaMateriaResponseMapper {
    MateriaDTO toResponse(MateriaInfo materiaInfo);
    List<MateriaDTO> toResponseList(List<MateriaInfo> materiasInfo);
}
