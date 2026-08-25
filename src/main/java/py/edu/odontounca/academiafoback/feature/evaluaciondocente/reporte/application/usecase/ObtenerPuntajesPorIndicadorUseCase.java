package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract.EvaluacionQueryRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PuntajeIndicadorInfo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObtenerPuntajesPorIndicadorUseCase {
    private final EvaluacionQueryRepository evaluacionQueryRepository;

    public Result execute(Query query){
        return new Result(
            this.evaluacionQueryRepository.obtenerPuntajeIndicadorPor(query.idPeriodo(), query.idEstamento(), query.idTipoDocente())
        );
    }

    public static record Query(Integer idPeriodo, Integer idEstamento, Integer idTipoDocente) {}
    public static record Result(List<PuntajeIndicadorInfo> detalles) {}
}
