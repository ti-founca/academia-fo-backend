package py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.usecase.BuscarEvaluacionPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.dto.EvaluacionDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.mapper.EvaluacionResponseMapper;

@RestController
@RequestMapping("/api/evaluaciones")
@RequiredArgsConstructor
public class EvaluacionController {
    private final BuscarEvaluacionPorIdUseCase buscarEvaluacionPorIdUseCase;
    private final EvaluacionResponseMapper mapper;

    @GetMapping("{id}")
    public EvaluacionDTO buscarPorId(@PathVariable("id") Integer id){
        BuscarEvaluacionPorIdUseCase.Result result = this.buscarEvaluacionPorIdUseCase.execute(new BuscarEvaluacionPorIdUseCase.Query(id));
        return this.mapper.toResponse(result.evaluacion());
    }
}
