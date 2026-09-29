package py.edu.odontounca.academiafoback.feature.nominadocente.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.dto.info.DocenteInfo;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.mapper.DocenteResultMapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.repository.NominaRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

@Service
@RequiredArgsConstructor
public class BuscarDocentePorNominaUseCase {
    private final NominaRepository repository;
    private final DocenteResultMapper mapper;

    public Result execute(Query query){
        if(!this.repository.existsById(query.nominaId())){
            throw new NotFoundError("Nomina", query.nominaId());
        }
        return new Result(
            this.mapper.toInfo(
                this.repository.findDocenteByNominaIdAndDocenteId(query.nominaId(), query.docenteId())
                    .orElseThrow(() -> new NotFoundError("Docente", query.docenteId()))
            )
        );
    }

    public static record Result(DocenteInfo docenteInfo){ }
    public static record Query(Integer nominaId, Integer docenteId){ }
}
