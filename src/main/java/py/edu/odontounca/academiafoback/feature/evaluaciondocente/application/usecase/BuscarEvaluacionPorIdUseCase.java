package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.info.EvaluacionInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.mapper.EvaluacionResultMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.repository.EvaluacionRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

@Service
@RequiredArgsConstructor
public class BuscarEvaluacionPorIdUseCase {
    private final EvaluacionRepository repository;
    private final EvaluacionResultMapper mapper;

    public BuscarEvaluacionPorIdUseCase.Result execute(BuscarEvaluacionPorIdUseCase.Query query){
        return this.repository.buscarPorId(query.id)
                .map(this.mapper::toResult)
                .orElseThrow(() -> new NotFoundError("Evaluacion", query.id()));
    }

    public static record Query(Integer id){}
    public static record Result(EvaluacionInfo evaluacion){}
}
