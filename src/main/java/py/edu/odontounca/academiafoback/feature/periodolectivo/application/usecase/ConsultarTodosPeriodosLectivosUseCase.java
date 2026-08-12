package py.edu.odontounca.academiafoback.feature.periodolectivo.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.periodolectivo.application.mapper.PeriodoLectivoResultMapper;
import py.edu.odontounca.academiafoback.feature.periodolectivo.application.dto.info.PeriodoLectivoInfo;
import py.edu.odontounca.academiafoback.feature.periodolectivo.domain.repository.PeriodoLectivoRepository;
import py.edu.odontounca.academiafoback.shared.filter.Filter;
import py.edu.odontounca.academiafoback.shared.pagination.Page;
import py.edu.odontounca.academiafoback.shared.pagination.PageRequest;

@Service
@RequiredArgsConstructor
public class ConsultarTodosPeriodosLectivosUseCase {
    private final PeriodoLectivoRepository repository;
    private final PeriodoLectivoResultMapper mapper;

    public Result execute(Query query){
        return new Result(
            this.mapper.toPageInfo(
                this.repository.findByFilter(query.filter(), query.pageRequest)
            )
        );
    }

    public static record Result(Page<PeriodoLectivoInfo> periodosLectivosInfoPage){ }
    public static record Query(Filter filter, PageRequest pageRequest){ }
}


