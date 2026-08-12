package py.edu.odontounca.academiafoback.feature.periodolectivo.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import py.edu.odontounca.academiafoback.feature.periodolectivo.application.usecase.ConsultarTodosPeriodosLectivosUseCase;
import py.edu.odontounca.academiafoback.feature.periodolectivo.presentation.dto.PeriodoLectivoDTO;
import py.edu.odontounca.academiafoback.feature.periodolectivo.presentation.mapper.PeriodoLectivoResponseMapper;
import py.edu.odontounca.academiafoback.shared.filter.Filter;
import py.edu.odontounca.academiafoback.shared.pagination.Page;
import py.edu.odontounca.academiafoback.shared.pagination.PageRequest;
import py.edu.odontounca.academiafoback.shared.pagination.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/periodos-lectivos")
@RequiredArgsConstructor
public class PeriodoLectivoController {
    private final PeriodoLectivoResponseMapper mapper;
    private final ConsultarTodosPeriodosLectivosUseCase consultarPeriodosLectivosUseCase;

    @GetMapping()
    public Page<PeriodoLectivoDTO> consultarTodos(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort
    ){
        System.out.println(sort);
        ConsultarTodosPeriodosLectivosUseCase.Result result = this.consultarPeriodosLectivosUseCase.execute(
            new ConsultarTodosPeriodosLectivosUseCase.Query(
                Filter.empty(),
                sort != null ? PageRequest.of(page, size, sort) : PageRequest.of(page, size)
            )
        );
        return mapper.toResponsePage(result.periodosLectivosInfoPage());
    }
}
