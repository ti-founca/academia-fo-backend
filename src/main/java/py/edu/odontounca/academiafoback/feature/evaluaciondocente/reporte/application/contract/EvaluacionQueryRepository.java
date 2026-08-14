package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.EvaluadoMateriaGeneralInfo;

import java.util.List;

public interface EvaluacionQueryRepository {
    List<EvaluadoMateriaGeneralInfo> consultarEvaluadosPor(Integer idPeriodo);
    Double obtenerPromedioPor(Integer idPeriodo, Integer idEvaluado, Integer idMateria, Integer idTipoDocente, Integer idEstamento);
    Double obtenerCantidadPromediadaPor(Integer idPeriodo, Integer idEvaluado, Integer idMateria, Integer idTipoDocente);
}
