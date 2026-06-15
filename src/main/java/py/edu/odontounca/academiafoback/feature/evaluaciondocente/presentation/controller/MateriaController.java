package py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.query.ConsultarPorIdQuery;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.result.MateriaResult;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.usecase.ConsultarPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.dto.MateriaDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.mapper.MateriaResponseMapper;

import java.util.Optional;

@RestController
@RequestMapping("/api/materias")
@RequiredArgsConstructor
public class MateriaController {
    private final ConsultarPorIdUseCase consultarPorIdUseCase;
    private final MateriaResponseMapper mapper;

    @GetMapping("{id}")
    public MateriaDTO getPorId(@PathVariable("id") Integer id){
        MateriaResult materiaResult = this.consultarPorIdUseCase.execute((new ConsultarPorIdQuery(id)));
        return this.mapper.toDTO(materiaResult);
    }
}
