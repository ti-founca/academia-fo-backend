package py.edu.odontounca.academiafoback.feature.estamento.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.estamento.application.dto.info.EstamentoInfo;
import py.edu.odontounca.academiafoback.feature.estamento.application.mapper.EstamentoResultMapper;
import py.edu.odontounca.academiafoback.feature.estamento.domain.repository.EstamentoRepository;
import py.edu.odontounca.academiafoback.shared.filter.Filter;
import py.edu.odontounca.academiafoback.shared.pagination.Page;
import py.edu.odontounca.academiafoback.shared.pagination.PageRequest;

@Service
@RequiredArgsConstructor
public class ConsultarTodosEstamentosUseCase {
    private final EstamentoRepository repository;
    private final EstamentoResultMapper mapper;

    public Result execute(Query query){
        return new Result(
            this.mapper.toPageInfo(
                this.repository.findByFilter(query.filter(), query.pageRequest())
            )
        );
    }

    public static record Result(Page<EstamentoInfo> estamentosInfoPage){ }
    public static record Query(Filter filter, PageRequest pageRequest){ }
}
