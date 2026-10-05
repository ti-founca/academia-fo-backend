package py.edu.odontounca.academiafoback.feature.persona.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.edu.odontounca.academiafoback.feature.persona.application.usecase.BuscarPersonaPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.persona.presentation.dto.PersonaDTO;
import py.edu.odontounca.academiafoback.feature.persona.presentation.mapper.PersonaResponseMapper;

@RestController
@RequestMapping("/api/personas")
@RequiredArgsConstructor
public class PersonaController {
    private final BuscarPersonaPorIdUseCase buscarPersonaPorIdUseCase;
    private final PersonaResponseMapper mapper;

    @GetMapping("{id}")
    public PersonaDTO buscarPorId(@PathVariable("id") Integer id){
        BuscarPersonaPorIdUseCase.Result result = this.buscarPersonaPorIdUseCase.execute(new BuscarPersonaPorIdUseCase.Query(id));
        return this.mapper.toResponse(result.persona());
    }
}
