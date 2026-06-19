package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.usecase;

import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.query.BuscarDetalleEvaluacionPorIdQuery;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.result.DetalleEvaluacionResult;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.mapper.DetalleEvaluacionResultMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.repository.DetalleEvaluacionRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

@Service
public class BuscarDetalleEvaluacionPorIdUseCase {
    private final DetalleEvaluacionRepository repository;
    private final DetalleEvaluacionResultMapper mapper;

    public BuscarDetalleEvaluacionPorIdUseCase(DetalleEvaluacionRepository repository, DetalleEvaluacionResultMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public DetalleEvaluacionResult execute(BuscarDetalleEvaluacionPorIdQuery query){
        return this.repository.buscarPorId(query.id())
                .map(mapper::toResult)
                .orElseThrow(() -> new NotFoundError("DetalleEvaluacion", query.id()));
    }
}
