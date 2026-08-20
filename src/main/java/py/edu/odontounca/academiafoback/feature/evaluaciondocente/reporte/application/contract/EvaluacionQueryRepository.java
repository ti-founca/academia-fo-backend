package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.EvaluadoInfo;

import java.util.List;

public interface EvaluacionQueryRepository {
    List<EvaluadoInfo> consultarEvaluadosPor(Integer idPeriodo);
    List<EvaluadoInfo> consultarEvaluadosPor(Integer idPeriodo, Integer idEstamento);
    Double obtenerPromedioPor(Integer idPeriodo, Integer idEvaluado, Integer idMateria, Integer idTipoDocente, Integer idEstamento);
    Double obtenerCantidadPromediadaPor(Integer idPeriodo, Integer idEvaluado, Integer idMateria, Integer idTipoDocente);
}
