package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.info.MateriaInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.usecase.BuscarMateriaPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Materia;

@Mapper(componentModel = "spring")
public interface MateriaResultMapper {
    BuscarMateriaPorIdUseCase.Result toResult(Materia materia);
    MateriaInfo toInfo(Materia materia);
}
