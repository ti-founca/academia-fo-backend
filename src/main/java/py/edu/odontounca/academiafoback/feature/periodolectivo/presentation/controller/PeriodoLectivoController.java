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

import java.util.List;

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
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortDir
    ){
        ConsultarTodosPeriodosLectivosUseCase.Result result = this.consultarPeriodosLectivosUseCase.execute(
            new ConsultarTodosPeriodosLectivosUseCase.Query(
                Filter.empty(),
                sortBy != null ? PageRequest.of(page, size, Sort.by(sortBy, toDirection(sortDir))) : PageRequest.of(page, size)
            )
        );
        return mapper.toResponsePage(result.periodosLectivosInfoPage());
    }

    private Sort.Direction toDirection(String dir){
        if (dir == null) return Sort.Direction.ASC;
        if (dir.equals("asc")) return Sort.Direction.ASC;
        if (dir.equals("desc")) return Sort.Direction.DESC;
        return Sort.Direction.ASC;
    }
}
