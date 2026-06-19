package py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.query.BuscarDetalleEvaluacionPorIdQuery;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.result.DetalleEvaluacionResult;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.usecase.BuscarDetalleEvaluacionPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.dto.DetalleEvaluacionDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.mapper.DetalleEvaluacionResponseMapper;

@RestController
@RequestMapping("/api/evaluaciones/detalles")
@RequiredArgsConstructor
public class DetalleEvaluacionController {
    private final BuscarDetalleEvaluacionPorIdUseCase buscarDetalleEvaluacionPorIdUseCase;
    private final DetalleEvaluacionResponseMapper resultMapper;

    @GetMapping("{id}")
    public DetalleEvaluacionDTO buscarPorId(@PathVariable("id") Integer id){
        DetalleEvaluacionResult result = this.buscarDetalleEvaluacionPorIdUseCase.execute(new BuscarDetalleEvaluacionPorIdQuery(id));
        return resultMapper.toResponse(result);

    }
}
