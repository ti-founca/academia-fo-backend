package py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.query.BuscarIndicadorPorIdQuery;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.result.IndicadorResult;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.usecase.BuscarIndicadorPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.dto.IndicadorDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.mapper.IndicadorResponseMapper;

@RestController
@RequestMapping("/api/indicadores")
@RequiredArgsConstructor
public class IndicadorController {

    private final BuscarIndicadorPorIdUseCase buscarIndicadorPorIdUseCase;
    private final IndicadorResponseMapper mapper;

    @GetMapping("{id}")
    public IndicadorDTO buscarPorId(@PathVariable("id") Integer id){
        IndicadorResult indicadorResult = buscarIndicadorPorIdUseCase.execute(new BuscarIndicadorPorIdQuery(id));
        return this.mapper.toResponse(indicadorResult);
    }

}
