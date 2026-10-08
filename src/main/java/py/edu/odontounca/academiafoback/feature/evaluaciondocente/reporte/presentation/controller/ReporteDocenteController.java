package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.ObtenerPuntajesDocentesUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.PuntajeDocenteDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper.PuntajeDocenteResponseMapper;

import java.util.List;

@RestController
@RequestMapping("/api/periodos-lectivos/{idPeriodo}/reportes")
@RequiredArgsConstructor
public class ReporteDocenteController {
    private final ObtenerPuntajesDocentesUseCase obtenerPuntajesDocentesUseCase;

    private final PuntajeDocenteResponseMapper puntajeDocenteResponseMapper;

    @GetMapping("docentes")
    public List<PuntajeDocenteDTO> consultarPuntajesDocentes(@PathVariable Integer idPeriodo){
        ObtenerPuntajesDocentesUseCase.Result result = this.obtenerPuntajesDocentesUseCase.execute(
                new ObtenerPuntajesDocentesUseCase.Query(idPeriodo, null)
        );
        return this.puntajeDocenteResponseMapper.toResponseList(result.puntajes());
    }

    @GetMapping("docentes/{idDocente}")
    public PuntajeDocenteDTO consultarPuntajesDocente(
            @PathVariable Integer idPeriodo,
            @PathVariable Integer idDocente
    ){
        ObtenerPuntajesDocentesUseCase.Result result = this.obtenerPuntajesDocentesUseCase.execute(
                new ObtenerPuntajesDocentesUseCase.Query(idPeriodo, idDocente)
        );
        return this.puntajeDocenteResponseMapper.toResponse(result.puntajes().getFirst());
    }
}
