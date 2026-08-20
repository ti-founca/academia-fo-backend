package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PuntajeGeneralInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.ObtenerPuntajesPorEstamentoUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.PuntajePorEstamentoDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PuntajePorEstamentoResponseMapper {

    @Mapping(source = "estamento.id", target = "estamento.id")
    @Mapping(source = "estamento.descripcion", target = "estamento.descripcion")
    @Mapping(source = "detalles", target = "puntajes", qualifiedByName = "toPuntajeList")
    PuntajePorEstamentoDTO toResponse(ObtenerPuntajesPorEstamentoUseCase.Result result);

    @Named("toPuntajeList")
    default List<PuntajePorEstamentoDTO.Puntaje> toPuntajeList(List<PuntajeGeneralInfo> puntajeInfo){
        return puntajeInfo
            .stream()
            .map(pInfo -> new PuntajePorEstamentoDTO.Puntaje(
                    pInfo.evaluado().nombres() + " " + pInfo.evaluado().apellidos(),
                    pInfo.materia().descripcion(),
                    pInfo.materia().codigo().trim(),
                    pInfo.tipoDocente().descripcion(),
                    pInfo.promedioGeneral().doubleValue()
            ))
            .toList();
    }
}
