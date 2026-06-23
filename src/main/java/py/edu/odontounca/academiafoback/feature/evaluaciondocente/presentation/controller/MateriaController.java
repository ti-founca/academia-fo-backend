package py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.usecase.BuscarMateriaPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.dto.MateriaDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.mapper.MateriaResponseMapper;

@RestController
@RequestMapping("/api/materias")
@RequiredArgsConstructor
public class MateriaController {
    private final BuscarMateriaPorIdUseCase buscarMateriaPorIdUseCase;
    private final MateriaResponseMapper mapper;

    @GetMapping("{id}")
    public MateriaDTO getPorId(@PathVariable("id") Integer id){
        BuscarMateriaPorIdUseCase.Result materiaResult = this.buscarMateriaPorIdUseCase.execute((new BuscarMateriaPorIdUseCase.Query(id)));
        return this.mapper.toDTO(materiaResult.materia());
    }
}
