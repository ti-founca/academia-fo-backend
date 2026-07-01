package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.repository;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.info.EvaluadoMateriaGeneralInfo;

import java.util.List;

public interface EvaluacionQueryRepository {
    List<EvaluadoMateriaGeneralInfo> consultarEvaluadosPorPeriodo(Integer idPeriodo);
    Double obtenerPromedioPor(Integer idPeriodo, Integer idEvaluado, Integer idMateria, Integer idTipoDocente, Integer idEstamento);
    Double obtenerCantidadPromediadaPor(Integer idPeriodo, Integer idEvaluado, Integer idMateria, Integer idTipoDocente);
}
