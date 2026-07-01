package py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.info.EvaluacionGeneralDocenteMateriaInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.usecase.BuscarEvaluacionPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.usecase.ObtenerPuntajesGeneralesUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.dto.EvaluacionDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.dto.EvaluacionGeneralDocenteMateriaDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.mapper.EvaluacionGeneralDocenteMateriaResponseMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.mapper.EvaluacionResponseMapper;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
@RequiredArgsConstructor
public class EvaluacionController {
    private final BuscarEvaluacionPorIdUseCase buscarEvaluacionPorIdUseCase;
    private final ObtenerPuntajesGeneralesUseCase obtenerPuntajesGeneralesUseCase;

    private final EvaluacionResponseMapper evaluacionMapper;
    private final EvaluacionGeneralDocenteMateriaResponseMapper evaluacionGeneralMapper;

    @GetMapping("{id}")
    public EvaluacionDTO buscarPorId(@PathVariable("id") Integer id){
        BuscarEvaluacionPorIdUseCase.Result result = this.buscarEvaluacionPorIdUseCase.execute(new BuscarEvaluacionPorIdUseCase.Query(id));
        return this.evaluacionMapper.toResponse(result.evaluacion());
    }

    @GetMapping("puntajes/general")
    public List<EvaluacionGeneralDocenteMateriaDTO> consultarPuntajesGenerales(
            @RequestParam() Integer idPeriodo
    ){
        ObtenerPuntajesGeneralesUseCase.Result result = this.obtenerPuntajesGeneralesUseCase.execute(new ObtenerPuntajesGeneralesUseCase.Query(idPeriodo));
        return result.detalles().stream().map(this.evaluacionGeneralMapper::toResponse).toList();
    }
}
