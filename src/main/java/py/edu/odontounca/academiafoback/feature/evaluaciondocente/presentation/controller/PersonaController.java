package py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.query.BuscarPersonaPorIdQuery;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.result.PersonaResult;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.usecase.BuscarPersonaPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.dto.PersonaDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.mapper.PersonaResponseMapper;

@RestController
@RequestMapping("/api/evaluaciones/personas")
@RequiredArgsConstructor
public class PersonaController {
    private final BuscarPersonaPorIdUseCase buscarPersonaPorIdUseCase;
    private final PersonaResponseMapper mapper;

    @GetMapping("{id}")
    public PersonaDTO buscarPorId(@PathVariable("id") Integer id){
        PersonaResult result = this.buscarPersonaPorIdUseCase.execute(new BuscarPersonaPorIdQuery(id));
        return this.mapper.toResponse(result);
    }
}
