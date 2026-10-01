package py.edu.odontounca.academiafoback.feature.nominadocente.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.contract.NominaResumenQueryRepository;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.dto.info.NominaResumenInfo;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.repository.PeriodoLectivoRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultarNominasPorPeriodoLectivoUseCase {
    private final NominaResumenQueryRepository nominaResumenQueryRepository;
    private final PeriodoLectivoRepository periodoLectivoRepository;

    public Result execute(Query query){
        if(!this.periodoLectivoRepository.existsById(query.periodoLectivoId())){
            throw new NotFoundError("PeriodoLectivo", query.periodoLectivoId());
        }
        return new Result(
            this.nominaResumenQueryRepository.consultarPorPeriodoLectivo(query.periodoLectivoId())
        );
    }

    public static record Result(List<NominaResumenInfo> nominasResumenInfo){ }
    public static record Query(Integer periodoLectivoId){ }
}
