package py.edu.odontounca.academiafoback.feature.nominadocente.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.usecase.ConsultarNominasPorPeriodoLectivoUseCase;
import py.edu.odontounca.academiafoback.feature.nominadocente.presentation.dto.NominaResumenDTO;
import py.edu.odontounca.academiafoback.feature.nominadocente.presentation.mapper.NominaResumenResponseMapper;

import java.util.List;

@RestController
@RequestMapping("/api/periodos-lectivos/{idPeriodo}/nominas")
@RequiredArgsConstructor
public class PeriodoLectivoNominaController {
    private final NominaResumenResponseMapper mapper;
    private final ConsultarNominasPorPeriodoLectivoUseCase consultarNominasPorPeriodoLectivoUseCase;

    @GetMapping()
    public List<NominaResumenDTO> consultarNominas(@PathVariable Integer idPeriodo){
        ConsultarNominasPorPeriodoLectivoUseCase.Result result = this.consultarNominasPorPeriodoLectivoUseCase.execute(
            new ConsultarNominasPorPeriodoLectivoUseCase.Query(idPeriodo)
        );
        return mapper.toResponseList(result.nominasResumenInfo());
    }
}
