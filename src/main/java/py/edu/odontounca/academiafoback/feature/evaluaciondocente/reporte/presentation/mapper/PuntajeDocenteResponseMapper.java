package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.MateriaInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PuntajeDocenteInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PuntajeMateriaInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.TipoDocenteInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.MateriaDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.PuntajeDocenteDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.PuntajeMateriaDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.TipoDocenteDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PuntajeDocenteResponseMapper {

    @Mapping(target = "idDocente", source = "id")
    @Mapping(target = "nombres", source = "nombres", qualifiedByName = "trim")
    @Mapping(target = "apellidos", source = "apellidos", qualifiedByName = "trim")
    @Mapping(target = "ci", source = "ci", qualifiedByName = "trim")
    @Mapping(target = "puntajes", source = "puntajeMateriaList")
    PuntajeDocenteDTO toResponse(PuntajeDocenteInfo puntajeDocenteInfo);

    List<PuntajeDocenteDTO> toResponseList(List<PuntajeDocenteInfo> puntajeDocenteInfoList);

    @Mapping(target = "promedioGeneral", source = "promedioGeneral", qualifiedByName = "bigDecimalToDouble")
    PuntajeMateriaDTO toResponse(PuntajeMateriaInfo puntajeMateriaInfo);

    default MateriaDTO toResponse(MateriaInfo materiaInfo){
        MateriaDTO materiaDTO = new MateriaDTO();
        materiaDTO.setId(materiaInfo.id());
        materiaDTO.setDescripcion(trim(materiaInfo.descripcion()));
        materiaDTO.setCodigo(trim(materiaInfo.codigo()));
        return materiaDTO;
    }

    default TipoDocenteDTO toResponse(TipoDocenteInfo tipoDocenteInfo){
        return new TipoDocenteDTO(tipoDocenteInfo.id(), trim(tipoDocenteInfo.descripcion()));
    }

    @Named("trim")
    default String trim(String valor){
        return valor == null ? null : valor.trim();
    }

    @Named("bigDecimalToDouble")
    default double toDouble(BigDecimal valor){
        return valor.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
