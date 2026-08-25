package py.edu.odontounca.academiafoback.feature.curso.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import py.edu.odontounca.academiafoback.feature.curso.application.dto.CursoInfo;
import py.edu.odontounca.academiafoback.feature.curso.domain.model.Curso;
import py.edu.odontounca.academiafoback.feature.curso.domain.model.vo.OrdenSemestre;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CursoInfoMapper {
    @Mapping(source = "ordenSemestre", target = "ordenSemestre", qualifiedByName = "ordenSemestreIntVal")
    CursoInfo toInfo(Curso curso);
    List<CursoInfo> toInfoList(List<Curso> cursoList);

    @Named("ordenSemestreIntVal")
    default Integer ordenSemestreIntVal(OrdenSemestre ordenSemestre){
        return ordenSemestre.getCode();
    }
}
