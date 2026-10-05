package py.edu.odontounca.academiafoback.feature.nominadocente.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.dto.info.MateriaInfo;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.model.Materia;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NominaMateriaResultMapper {
    MateriaInfo toInfo(Materia materia);
    List<MateriaInfo> toInfoList(List<Materia> materias);
}
