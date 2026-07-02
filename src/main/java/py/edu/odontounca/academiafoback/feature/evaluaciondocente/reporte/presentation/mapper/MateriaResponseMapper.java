package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.MateriaInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.MateriaDTO;

@Mapper(componentModel = "spring")
public interface MateriaResponseMapper {
    MateriaDTO toDTO(MateriaInfo materiaInfo);
}
