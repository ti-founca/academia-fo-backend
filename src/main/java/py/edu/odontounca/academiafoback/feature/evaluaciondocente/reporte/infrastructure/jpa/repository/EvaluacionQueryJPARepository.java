package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.EvaluadoInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PuntajeIndicadorInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.dto.PuntajeIndicadorProjection;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.EvaluacionJPAModel;

import java.util.List;
import java.util.Optional;

public interface EvaluacionQueryJPARepository extends JpaRepository<EvaluacionJPAModel, Integer>, PuntajePorIndicadorJPARepository {
    @Query("SELECT DISTINCT " +
                    "e.evaluado.id AS idEvaluado," +
                    "e.materia.id as idMateria," +
                    "e.formulario.tipoEvaluacion.tipoDocente.id as idTipoDocente " +
                    "FROM EvaluacionJPAModel e " +
                    "WHERE e.formulario.idPeriodo = :idPeriodo AND e.terminado = true")
    List<EvaluadoInfo> consultarEvaluadosPor(@Param("idPeriodo") Integer idPeriodo);

    @Query("SELECT DISTINCT " +
            "e.evaluado.id AS idEvaluado," +
            "e.materia.id as idMateria," +
            "e.formulario.tipoEvaluacion.tipoDocente.id as idTipoDocente " +
            "FROM EvaluacionJPAModel e " +
            "WHERE e.formulario.idPeriodo = :idPeriodo " +
            "AND e.formulario.tipoEvaluacion.estamento.id = :idEstamento " +
            "AND e.terminado = true")
    List<EvaluadoInfo> consultarEvaluadosPor(@Param("idPeriodo") Integer idPeriodo, @Param("idEstamento") Integer idEstamento);

    @Query("SELECT AVG(de.puntaje) " +
            "FROM DetalleEvaluacionJPAModel de " +
            "WHERE de.evaluacion.terminado = true " +
            "AND de.evaluacion.formulario.idPeriodo = :idPeriodo " +
            "AND de.evaluacion.evaluado.id = :idEvaluado " +
            "AND de.evaluacion.materia.id = :idMateria " +
            "AND de.evaluacion.formulario.tipoEvaluacion.tipoDocente.id = :idTipoDocente " +
            "AND de.evaluacion.formulario.tipoEvaluacion.estamento.id = :idEstamento")
    Optional<Double> obtenerPromedioPor(
            @Param("idPeriodo") Integer idPeriodo,
            @Param("idEvaluado") Integer idEvaluado,
            @Param("idMateria") Integer idMateria,
            @Param("idTipoDocente") Integer idTipoDocente,
            @Param("idEstamento") Integer idEstamento
    );

    @Query("SELECT COUNT(de) " +
            "FROM DetalleEvaluacionJPAModel de " +
            "WHERE de.evaluacion.terminado = true " +
            "AND de.evaluacion.formulario.idPeriodo = :idPeriodo " +
            "AND de.evaluacion.evaluado.id = :idEvaluado " +
            "AND de.evaluacion.materia.id = :idMateria " +
            "AND de.evaluacion.formulario.tipoEvaluacion.tipoDocente.id = :idTipoDocente")
    Double obtenerCantidadPromediadadPor(
            @Param("idPeriodo") Integer idPeriodo,
            @Param("idEvaluado") Integer idEvaluado,
            @Param("idMateria") Integer idMateria,
            @Param("idTipoDocente") Integer idTipoDocente
    );

    /*@Query("SELECT de.indicador as indicador," +
            "de.evaluacion.formulario.tipoEvaluacion.tipoDocente as tipoDocente," +
            "de.evaluacion.formulario.tipoEvaluacion.estamento as estamento," +
            "AVG(de.puntaje) as promedio," +
            "COUNT(de) as cantidad " +
            "FROM DetalleEvaluacionJPAModel de " +
            "WHERE de.evaluacion.formulario.idPeriodo = :idPeriodo " +
            "AND de.evaluacion.terminado = TRUE " +
            "GROUP BY de.indicador, de.evaluacion.formulario.tipoEvaluacion.tipoDocente, de.evaluacion.formulario.tipoEvaluacion.estamento")
    public List<PuntajeIndicadorProjection> obtenerPuntajeIndicador(Integer idPeriodo);*/

}
