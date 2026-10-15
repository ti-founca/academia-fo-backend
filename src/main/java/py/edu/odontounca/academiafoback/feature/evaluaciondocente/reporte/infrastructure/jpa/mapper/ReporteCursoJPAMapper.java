package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.CursoInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Curso;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.CursoJPAModel;

@Mapper(componentModel = "spring")
public interface ReporteCursoJPAMapper {
    default Curso toDomain(CursoJPAModel cursoJpa){
        return Curso.reconstruir(cursoJpa.getId(), cursoJpa.getDescripcion(), cursoJpa.getSemestre(), cursoJpa.getAnho());
    }

    CursoInfo toInfo(CursoJPAModel cursoJpa);
}
