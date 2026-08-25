package py.edu.odontounca.academiafoback.feature.curso.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.curso.domain.model.Curso;
import py.edu.odontounca.academiafoback.feature.curso.domain.model.vo.OrdenSemestre;
import py.edu.odontounca.academiafoback.feature.curso.infrastructure.jpa.model.CursoJPAModel;

@Mapper(componentModel = "spring")
public interface CursoJPAMapper {
    default Curso toDomain(CursoJPAModel cursoJpa){
        return Curso.reconstruir(
                cursoJpa.getId(),
                cursoJpa.getDescripcion(),
                cursoJpa.getAnho(),
                cursoJpa.getNroSemestre(),
                OrdenSemestre.fromCode(cursoJpa.getOrdenSemestre())
        );
    };
}
