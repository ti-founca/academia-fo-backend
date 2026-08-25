package py.edu.odontounca.academiafoback.feature.curso.presentation.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.curso.application.dto.CursoInfo;
import py.edu.odontounca.academiafoback.feature.curso.presentation.dto.CursoDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CursoResponseMapper {
    CursoDTO toResponse(CursoInfo cursoInfo);
    List<CursoDTO> toResponseList(List<CursoInfo> cursoInfoList);
}
