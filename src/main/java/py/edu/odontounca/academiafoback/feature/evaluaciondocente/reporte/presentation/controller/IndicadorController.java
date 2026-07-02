package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.BuscarIndicadorPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.IndicadorDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper.IndicadorResponseMapper;

@RestController
@RequestMapping("/api/indicadores")
@RequiredArgsConstructor
public class IndicadorController {

    private final BuscarIndicadorPorIdUseCase buscarIndicadorPorIdUseCase;
    private final IndicadorResponseMapper mapper;

    @GetMapping("{id}")
    public IndicadorDTO buscarPorId(@PathVariable("id") Integer id){
        BuscarIndicadorPorIdUseCase.Result indicadorResult = buscarIndicadorPorIdUseCase.execute(new BuscarIndicadorPorIdUseCase.Query(id));
        return this.mapper.toResponse(indicadorResult.indicador());
    }

}
