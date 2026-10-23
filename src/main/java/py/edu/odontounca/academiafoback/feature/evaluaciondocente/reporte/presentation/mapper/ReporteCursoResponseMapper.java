package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.CursoInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.CursoDTO;

@Mapper(componentModel = "spring")
public interface ReporteCursoResponseMapper {
    @Mapping(target = "descripcion", source = "descripcion", qualifiedByName = "trimCurso")
    CursoDTO toResponse(CursoInfo cursoInfo);

    @Named("trimCurso")
    default String trimCurso(String valor){
        return valor == null ? null : valor.trim();
    }
}
