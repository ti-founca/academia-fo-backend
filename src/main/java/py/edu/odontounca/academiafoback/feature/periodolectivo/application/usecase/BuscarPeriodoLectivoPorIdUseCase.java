package py.edu.odontounca.academiafoback.feature.periodolectivo.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.periodolectivo.application.dto.info.PeriodoLectivoInfo;
import py.edu.odontounca.academiafoback.feature.periodolectivo.application.mapper.PeriodoLectivoResultMapper;
import py.edu.odontounca.academiafoback.feature.periodolectivo.domain.repository.PeriodoLectivoRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

@Service
@RequiredArgsConstructor
public class BuscarPeriodoLectivoPorIdUseCase {
    private final PeriodoLectivoRepository repository;
    private final PeriodoLectivoResultMapper mapper;

    public Result execute(Query query){
        return this.repository.findById(query.id())
                .map(periodoLectivo -> new Result(this.mapper.toInfo(periodoLectivo)))
                .orElseThrow(() -> new NotFoundError("PeriodoLectivo", query.id()));
    }

    public static record Query(Integer id){ }
    public static record Result(PeriodoLectivoInfo periodoLectivo){ }
}
