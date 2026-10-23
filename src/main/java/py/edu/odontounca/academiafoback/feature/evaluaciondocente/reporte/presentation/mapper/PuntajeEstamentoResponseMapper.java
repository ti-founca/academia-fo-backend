package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PuntajeEstamentoInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.PuntajeEstamentoDTO;

@Mapper(componentModel = "spring")
public interface PuntajeEstamentoResponseMapper {
    @Mapping(target = "idEstamento", source = "estamento.id")
    @Mapping(target = "estamento", source = "estamento.descripcion")
    @Mapping(target = "ponderacion", source = "estamento.porcentajePeso")
    PuntajeEstamentoDTO toResponse(PuntajeEstamentoInfo puntajeEstamentoInfo);
}
