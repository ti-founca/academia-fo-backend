package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.MateriaInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PersonaInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PuntajeGeneralDetalleInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PuntajeGeneralInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.TipoDocenteInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.MateriaDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.PersonaDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.PuntajeDocenteMateriaDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.TipoDocenteDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Mapper(componentModel = "spring", uses = ReporteCursoResponseMapper.class)
public interface PuntajeDocenteMateriaResponseMapper {

    @Mapping(target = "docente", source = "evaluado")
    @Mapping(target = "puntajeGeneral", source = "promedioGeneral", qualifiedByName = "formatearPuntaje")
    @Mapping(target = "puntajePorEstamento", source = "detalles")
    PuntajeDocenteMateriaDTO toResponse(PuntajeGeneralInfo puntajeGeneralInfo);

    List<PuntajeDocenteMateriaDTO> toResponseList(List<PuntajeGeneralInfo> puntajeGeneralInfoList);

    @Mapping(target = "puntaje", source = "promedio", qualifiedByName = "formatearPuntaje")
    @Mapping(target = "puntajePonderado", source = "promedioPonderado", qualifiedByName = "formatearPuntaje")
    PuntajeDocenteMateriaDTO.PuntajeEstamento toResponse(PuntajeGeneralDetalleInfo detalle);

    @Mapping(target = "nombres", source = "nombres", qualifiedByName = "trim")
    @Mapping(target = "apellidos", source = "apellidos", qualifiedByName = "trim")
    @Mapping(target = "ci", source = "ci", qualifiedByName = "trim")
    PersonaDTO toResponse(PersonaInfo personaInfo);

    @Mapping(target = "descripcion", source = "descripcion", qualifiedByName = "trim")
    @Mapping(target = "codigo", source = "codigo", qualifiedByName = "trim")
    MateriaDTO toResponse(MateriaInfo materiaInfo);

    @Mapping(target = "descripcion", source = "descripcion", qualifiedByName = "trim")
    TipoDocenteDTO toResponse(TipoDocenteInfo tipoDocenteInfo);

    @Named("trim")
    default String trim(String valor){
        return valor == null ? null : valor.trim();
    }

    @Named("formatearPuntaje")
    default String formatearPuntaje(BigDecimal valor){
        return valor == null ? null : valor.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }
}
