package py.edu.odontounca.academiafoback.feature.nominadocente.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.usecase.ConsultarDocentesPorPeriodoLectivoUseCase;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.usecase.ConsultarMateriasPorDocenteYPeriodoLectivoUseCase;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.usecase.ConsultarNominasPorPeriodoLectivoUseCase;
import py.edu.odontounca.academiafoback.feature.nominadocente.presentation.dto.DocenteDTO;
import py.edu.odontounca.academiafoback.feature.nominadocente.presentation.dto.MateriaDTO;
import py.edu.odontounca.academiafoback.feature.nominadocente.presentation.dto.NominaResumenDTO;
import py.edu.odontounca.academiafoback.feature.nominadocente.presentation.mapper.DocenteResponseMapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.presentation.mapper.NominaMateriaResponseMapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.presentation.mapper.NominaResumenResponseMapper;

import java.util.List;

@RestController
@RequestMapping("/api/periodos-lectivos/{idPeriodo}")
@RequiredArgsConstructor
public class PeriodoLectivoNominaController {
    private final NominaResumenResponseMapper mapper;
    private final DocenteResponseMapper docenteMapper;
    private final NominaMateriaResponseMapper materiaMapper;
    private final ConsultarNominasPorPeriodoLectivoUseCase consultarNominasPorPeriodoLectivoUseCase;
    private final ConsultarDocentesPorPeriodoLectivoUseCase consultarDocentesPorPeriodoLectivoUseCase;
    private final ConsultarMateriasPorDocenteYPeriodoLectivoUseCase consultarMateriasPorDocenteYPeriodoLectivoUseCase;

    @GetMapping("/nominas")
    public List<NominaResumenDTO> consultarNominas(@PathVariable Integer idPeriodo){
        ConsultarNominasPorPeriodoLectivoUseCase.Result result = this.consultarNominasPorPeriodoLectivoUseCase.execute(
            new ConsultarNominasPorPeriodoLectivoUseCase.Query(idPeriodo)
        );
        return mapper.toResponseList(result.nominasResumenInfo());
    }

    @GetMapping("/docentes")
    public List<DocenteDTO> consultarDocentes(@PathVariable Integer idPeriodo){
        ConsultarDocentesPorPeriodoLectivoUseCase.Result result = this.consultarDocentesPorPeriodoLectivoUseCase.execute(
            new ConsultarDocentesPorPeriodoLectivoUseCase.Query(idPeriodo)
        );
        return docenteMapper.toResponseList(result.docentesInfo());
    }

    @GetMapping("/docentes/{idDocente}/materias")
    public List<MateriaDTO> consultarMateriasDocente(@PathVariable Integer idPeriodo, @PathVariable Integer idDocente){
        ConsultarMateriasPorDocenteYPeriodoLectivoUseCase.Result result = this.consultarMateriasPorDocenteYPeriodoLectivoUseCase.execute(
            new ConsultarMateriasPorDocenteYPeriodoLectivoUseCase.Query(idPeriodo, idDocente)
        );
        return materiaMapper.toResponseList(result.materiasInfo());
    }
}
