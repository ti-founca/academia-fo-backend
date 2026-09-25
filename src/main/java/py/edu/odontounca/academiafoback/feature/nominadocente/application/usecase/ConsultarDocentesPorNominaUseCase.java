package py.edu.odontounca.academiafoback.feature.nominadocente.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.dto.info.DocenteInfo;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.mapper.DocenteResultMapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.repository.NominaRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultarDocentesPorNominaUseCase {
    private final NominaRepository repository;
    private final DocenteResultMapper mapper;

    public Result execute(Query query){
        if(!this.repository.existsById(query.nominaId())){
            throw new NotFoundError("Nomina", query.nominaId());
        }
        return new Result(
            this.mapper.toInfoList(
                this.repository.findDocentesByNominaId(query.nominaId())
            )
        );
    }

    public static record Result(List<DocenteInfo> docentesInfo){ }
    public static record Query(Integer nominaId){ }
}
