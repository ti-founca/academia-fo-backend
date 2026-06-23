package py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.info.IndicadorInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.info.DetalleEvaluacionInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.dto.DetalleEvaluacionDTO;

@Mapper(componentModel = "spring")
public interface DetalleEvaluacionResponseMapper {
    @Mapping(target = "indicador", source = "indicador", qualifiedByName = "indicadorConverter")
    DetalleEvaluacionDTO toResponse(DetalleEvaluacionInfo result);

    @Named("indicadorConverter")
    default DetalleEvaluacionDTO.Indicador indicadorConverter(IndicadorInfo indicador){
        return new DetalleEvaluacionDTO.Indicador(indicador.id(), indicador.descripcion());
    }
}
