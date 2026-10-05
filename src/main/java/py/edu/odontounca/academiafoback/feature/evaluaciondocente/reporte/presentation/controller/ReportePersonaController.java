package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.BuscarReportePersonaPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.PersonaDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper.ReportePersonaResponseMapper;

@RestController
@RequestMapping("/api/evaluaciones/personas")
@RequiredArgsConstructor
public class ReportePersonaController {
    private final BuscarReportePersonaPorIdUseCase buscarPersonaPorIdUseCase;
    private final ReportePersonaResponseMapper mapper;

    @GetMapping("{id}")
    public PersonaDTO buscarPorId(@PathVariable("id") Integer id){
        BuscarReportePersonaPorIdUseCase.Result result = this.buscarPersonaPorIdUseCase.execute(new BuscarReportePersonaPorIdUseCase.Query(id));
        return this.mapper.toResponse(result.persona());
    }
}
