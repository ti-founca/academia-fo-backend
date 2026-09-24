package py.edu.odontounca.academiafoback.feature.nominadocente.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.dto.info.NominaInfo;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.mapper.NominaResultMapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.repository.NominaRepository;
import py.edu.odontounca.academiafoback.shared.filter.Filter;
import py.edu.odontounca.academiafoback.shared.pagination.Page;
import py.edu.odontounca.academiafoback.shared.pagination.PageRequest;

@Service
@RequiredArgsConstructor
public class ConsultarTodasNominasUseCase {
    private final NominaRepository repository;
    private final NominaResultMapper mapper;

    public Result execute(Query query){
        return new Result(
            this.mapper.toPageInfo(
                this.repository.findByFilter(query.filter(), query.pageRequest())
            )
        );
    }

    public static record Result(Page<NominaInfo> nominasInfoPage){ }
    public static record Query(Filter filter, PageRequest pageRequest){ }
}
