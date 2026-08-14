package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PuntajeGeneralDetalleInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.DetalleEvaluacionGeneralDocenteMateriaDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Mapper(componentModel = "spring")
public interface DetalleEvaluacionGeneralDocenteMateriaResponseMapper {
    @Mapping(target = "estamento", source = "estamento.descripcion")
    @Mapping(target = "peso", source = "estamento.porcentajePeso", qualifiedByName = "getPeso")
    @Mapping(target = "promedio", source = "promedio", qualifiedByName = "bigDecimalToDouble")
    @Mapping(target = "promedioPonderado", source = "promedioPonderado", qualifiedByName = "bigDecimalToDouble")
    DetalleEvaluacionGeneralDocenteMateriaDTO toResponse(PuntajeGeneralDetalleInfo detalle);

    @Named("bigDecimalToString")
    default String bigDecimalToString(BigDecimal valor){
        return valor.setScale(2, RoundingMode.HALF_UP).toString();
    }

    @Named("bigDecimalToDouble")
    default double toDouble(BigDecimal valor){
        return valor.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Named("getPeso")
    default String getPeso(BigDecimal peso){
        return peso.setScale(2, RoundingMode.HALF_UP).toString() +"%";
    }
}
