package py.edu.odontounca.academiafoback.feature.curso.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import py.edu.odontounca.academiafoback.feature.curso.application.usecase.ConsultarTodosCursosUseCase;
import py.edu.odontounca.academiafoback.feature.curso.presentation.dto.CursoDTO;
import py.edu.odontounca.academiafoback.feature.curso.presentation.mapper.CursoResponseMapper;
import py.edu.odontounca.academiafoback.shared.filter.Filter;
import py.edu.odontounca.academiafoback.shared.pagination.Page;
import py.edu.odontounca.academiafoback.shared.pagination.PageRequest;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoController {
    private final ConsultarTodosCursosUseCase consultarTodosCursosUseCase;
    private final CursoResponseMapper responseMapper;

    @GetMapping
    public Page<CursoDTO> consultarTodos(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ){
        ConsultarTodosCursosUseCase.Result result = this.consultarTodosCursosUseCase.execute(
                new ConsultarTodosCursosUseCase.Query(
                        Filter.of(),
                        sort != null ? PageRequest.of(page, size, sort) :PageRequest.of(page, size)
                )
        );
        return Page.of(
                this.responseMapper.toResponseList(result.cursosPage().content()),
                result.cursosPage().page(),
                result.cursosPage().size(),
                result.cursosPage().totalElements()
        );
    }
}
