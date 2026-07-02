package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.DetalleEvaluacionInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper.DetalleEvaluacionResultMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository.DetalleEvaluacionRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

@Service
@RequiredArgsConstructor
public class BuscarDetalleEvaluacionPorIdUseCase {
    private final DetalleEvaluacionRepository repository;
    private final DetalleEvaluacionResultMapper mapper;

    public BuscarDetalleEvaluacionPorIdUseCase.Result execute(BuscarDetalleEvaluacionPorIdUseCase.Query query){
        return this.repository.buscarPorId(query.id())
                .map(mapper::toResult)
                .orElseThrow(() -> new NotFoundError("DetalleEvaluacion", query.id()));
    }

    public static record Query(Integer id){}
    public static record Result(DetalleEvaluacionInfo detalleEvaluacion){}
}
