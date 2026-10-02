package py.edu.odontounca.academiafoback.feature.nominadocente.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.dto.info.DocenteInfo;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.mapper.DocenteResultMapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.repository.NominaRepository;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.repository.PeriodoLectivoRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultarDocentesPorPeriodoLectivoUseCase {
    private final NominaRepository nominaRepository;
    private final PeriodoLectivoRepository periodoLectivoRepository;
    private final DocenteResultMapper mapper;

    public Result execute(Query query){
        if(!this.periodoLectivoRepository.existsById(query.periodoLectivoId())){
            throw new NotFoundError("PeriodoLectivo", query.periodoLectivoId());
        }
        return new Result(
            this.mapper.toInfoList(
                this.nominaRepository.findDocentesByPeriodoLectivoId(query.periodoLectivoId())
            )
        );
    }

    public static record Result(List<DocenteInfo> docentesInfo){ }
    public static record Query(Integer periodoLectivoId){ }
}
