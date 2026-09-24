package py.edu.odontounca.academiafoback.feature.nominadocente.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.usecase.ConsultarTodasNominasUseCase;
import py.edu.odontounca.academiafoback.feature.nominadocente.presentation.dto.NominaDTO;
import py.edu.odontounca.academiafoback.feature.nominadocente.presentation.mapper.NominaResponseMapper;
import py.edu.odontounca.academiafoback.shared.filter.Filter;
import py.edu.odontounca.academiafoback.shared.pagination.Page;
import py.edu.odontounca.academiafoback.shared.pagination.PageRequest;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/nominas")
@RequiredArgsConstructor
public class NominaController {
    private final NominaResponseMapper mapper;
    private final ConsultarTodasNominasUseCase consultarTodasNominasUseCase;

    @GetMapping()
    public Page<NominaDTO> consultarTodas(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer periodoLectivoId,
            @RequestParam(required = false) Integer planCurricularId
    ){
        List<Filter.Criteria> criterias = new ArrayList<>();
        if(nombre != null && !nombre.isBlank()) criterias.add(new Filter.Criteria("nombre", Filter.Operator.LIKE, nombre));
        if(periodoLectivoId != null) criterias.add(new Filter.Criteria("periodoLectivoId", Filter.Operator.EQUALS, periodoLectivoId));
        if(planCurricularId != null) criterias.add(new Filter.Criteria("planCurricularId", Filter.Operator.EQUALS, planCurricularId));

        ConsultarTodasNominasUseCase.Result result = this.consultarTodasNominasUseCase.execute(
            new ConsultarTodasNominasUseCase.Query(
                new Filter(criterias),
                sort != null ? PageRequest.of(page, size, sort) : PageRequest.of(page, size)
            )
        );
        return mapper.toResponsePage(result.nominasInfoPage());
    }
}
