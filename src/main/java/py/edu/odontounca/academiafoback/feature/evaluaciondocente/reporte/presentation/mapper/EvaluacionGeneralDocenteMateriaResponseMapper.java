package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.DetalleEvaluacionGeneralDocenteMateriaDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.EvaluacionGeneralDocenteMateriaDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class EvaluacionGeneralDocenteMateriaResponseMapper {

    @Autowired
    private DetalleEvaluacionGeneralDocenteMateriaResponseMapper detalleEvaluacionMapper;

    @Mapping(target = "docente", source = "evaluado", qualifiedByName = "getDocente")
    @Mapping(target = "materia", source = "materia", qualifiedByName = "getMateria")
    @Mapping(target = "codigoMateria", source = "materia", qualifiedByName = "getCodigoMateria")
    @Mapping(target = "tipoDocente", source = "tipoDocente", qualifiedByName = "getTipoDocente")
    @Mapping(target = "promedioGeneral", source = "promedioGeneral", qualifiedByName = "bigDecimalToDouble")
    @Mapping(target = "detalle", source = "detalles", qualifiedByName = "getDetalles")
    public abstract EvaluacionGeneralDocenteMateriaDTO toResponse(PuntajeGeneralInfo evaluacionInfo);

    @Named("getDocente")
    String getDocente(PersonaInfo docente){
        return docente.nombres().trim() + " " + docente.apellidos().trim();
    }
    @Named("getMateria")
    String getMateria(MateriaInfo materiaInfo){
        return materiaInfo.descripcion().trim();
    }
    @Named("getCodigoMateria")
    String getCodigoMateria(MateriaInfo materiaInfo){
        return materiaInfo.codigo().trim();
    }
    @Named("getTipoDocente")
    String getTipoDocente(TipoDocenteInfo tipoDocenteInfo){
        return tipoDocenteInfo.descripcion().trim();
    }
    @Named("bigDecimalToDouble")
    double toDouble(BigDecimal valor){
        return valor.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Named("getDetalles")
    List<DetalleEvaluacionGeneralDocenteMateriaDTO> getDetalles(List<PuntajeGeneralDetalleInfo> detallesInfo){
        return detallesInfo.stream().map(this.detalleEvaluacionMapper::toResponse).toList();
    }
}
