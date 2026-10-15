package py.edu.odontounca.academiafoback.feature.estamento.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import py.edu.odontounca.academiafoback.feature.estamento.application.usecase.ConsultarTodosEstamentosUseCase;
import py.edu.odontounca.academiafoback.feature.estamento.presentation.dto.EstamentoDTO;
import py.edu.odontounca.academiafoback.feature.estamento.presentation.mapper.EstamentoResponseMapper;
import py.edu.odontounca.academiafoback.shared.filter.Filter;
import py.edu.odontounca.academiafoback.shared.pagination.Page;
import py.edu.odontounca.academiafoback.shared.pagination.PageRequest;

@RestController
@RequestMapping("/api/estamentos")
@RequiredArgsConstructor
public class EstamentoController {
    private final EstamentoResponseMapper mapper;
    private final ConsultarTodosEstamentosUseCase consultarTodosEstamentosUseCase;

    @GetMapping()
    public Page<EstamentoDTO> consultarTodos(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ){
        ConsultarTodosEstamentosUseCase.Result result = this.consultarTodosEstamentosUseCase.execute(
            new ConsultarTodosEstamentosUseCase.Query(
                Filter.empty(),
                sort != null ? PageRequest.of(page, size, sort) : PageRequest.of(page, size)
            )
        );
        return mapper.toResponsePage(result.estamentosInfoPage());
    }
}
