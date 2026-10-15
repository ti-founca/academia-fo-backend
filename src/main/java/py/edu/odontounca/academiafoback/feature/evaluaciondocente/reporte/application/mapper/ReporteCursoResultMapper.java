package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.CursoInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Curso;

@Mapper(componentModel = "spring")
public interface ReporteCursoResultMapper {
    CursoInfo toInfo(Curso curso);
}
