package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.info.EvaluadoMateriaGeneralInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.model.EvaluacionJPAModel;

import java.util.List;
import java.util.Optional;

public interface EvaluacionQueryJPARepository extends JpaRepository<EvaluacionJPAModel, Integer> {
    @Query("SELECT DISTINCT e.evaluado.id AS idEvaluado, e.materia.id as idMateria, e.formulario.tipoEvaluacion.tipoDocente.id as idTipoDocente FROM EvaluacionJPAModel e WHERE e.formulario.idPeriodo = :idPeriodo AND e.terminado = true")
    List<EvaluadoMateriaGeneralInfo> consultarEvaluadosPorPeriodoTipo(@Param("idPeriodo") Integer idPeriodo);

    @Query("SELECT AVG(de.puntaje) FROM DetalleEvaluacionJPAModel de WHERE de.evaluacion.terminado = true AND de.evaluacion.formulario.idPeriodo = :idPeriodo AND de.evaluacion.evaluado.id = :idEvaluado AND de.evaluacion.materia.id = :idMateria AND de.evaluacion.formulario.tipoEvaluacion.tipoDocente.id = :idTipoDocente AND de.evaluacion.formulario.tipoEvaluacion.estamento.id = :idEstamento")
    Optional<Double> obtenerPromedioPor(
            @Param("idPeriodo") Integer idPeriodo,
            @Param("idEvaluado") Integer idEvaluado,
            @Param("idMateria") Integer idMateria,
            @Param("idTipoDocente") Integer idTipoDocente,
            @Param("idEstamento") Integer idEstamento
    );

    @Query("SELECT COUNT(de) FROM DetalleEvaluacionJPAModel de WHERE de.evaluacion.terminado = true AND de.evaluacion.formulario.idPeriodo = :idPeriodo AND de.evaluacion.evaluado.id = :idEvaluado AND de.evaluacion.materia.id = :idMateria AND de.evaluacion.formulario.tipoEvaluacion.tipoDocente.id = :idTipoDocente")
    Double obtenerCantidadPromediadadPor(
            @Param("idPeriodo") Integer idPeriodo,
            @Param("idEvaluado") Integer idEvaluado,
            @Param("idMateria") Integer idMateria,
            @Param("idTipoDocente") Integer idTipoDocente
    );

}
