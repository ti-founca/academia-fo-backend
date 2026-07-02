package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.BuscarDetalleEvaluacionPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.DetalleEvaluacionDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper.DetalleEvaluacionResponseMapper;

@RestController
@RequestMapping("/api/evaluaciones/detalles")
@RequiredArgsConstructor
public class DetalleEvaluacionController {
    private final BuscarDetalleEvaluacionPorIdUseCase buscarDetalleEvaluacionPorIdUseCase;
    private final DetalleEvaluacionResponseMapper resultMapper;

    @GetMapping("{id}")
    public DetalleEvaluacionDTO buscarPorId(@PathVariable("id") Integer id){
        BuscarDetalleEvaluacionPorIdUseCase.Result result = this.buscarDetalleEvaluacionPorIdUseCase.execute(new BuscarDetalleEvaluacionPorIdUseCase.Query(id));
        return resultMapper.toResponse(result.detalleEvaluacion());
    }
}
