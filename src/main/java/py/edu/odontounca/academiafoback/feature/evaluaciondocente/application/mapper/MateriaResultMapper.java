package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.result.MateriaResult;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Materia;

@Mapper(componentModel = "spring")
public interface MateriaResultMapper {
    MateriaResult toResult(Materia materia);
}
