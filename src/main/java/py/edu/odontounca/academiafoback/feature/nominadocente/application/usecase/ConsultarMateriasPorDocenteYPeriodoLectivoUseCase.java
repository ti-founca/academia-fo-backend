package py.edu.odontounca.academiafoback.feature.nominadocente.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.dto.info.MateriaInfo;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.mapper.NominaMateriaResultMapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.repository.NominaRepository;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.repository.PeriodoLectivoRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultarMateriasPorDocenteYPeriodoLectivoUseCase {
    private final NominaRepository nominaRepository;
    private final PeriodoLectivoRepository periodoLectivoRepository;
    private final NominaMateriaResultMapper mapper;

    public Result execute(Query query){
        if(!this.periodoLectivoRepository.existsById(query.periodoLectivoId())){
            throw new NotFoundError("PeriodoLectivo", query.periodoLectivoId());
        }
        return new Result(
            this.mapper.toInfoList(
                this.nominaRepository.findMateriasByPeriodoLectivoIdAndDocenteId(query.periodoLectivoId(), query.docenteId())
            )
        );
    }

    public static record Result(List<MateriaInfo> materiasInfo){ }
    public static record Query(Integer periodoLectivoId, Integer docenteId){ }
}
