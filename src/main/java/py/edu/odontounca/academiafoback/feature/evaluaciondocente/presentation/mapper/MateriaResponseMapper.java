package py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.info.MateriaInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.dto.MateriaDTO;

@Mapper(componentModel = "spring")
public interface MateriaResponseMapper {
    MateriaDTO toDTO(MateriaInfo materiaInfo);
}
