package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.NominaDetalleJPAModel;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.NominaJPAModel;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.repository.query.DocentePorPeriodoJPAQuery;

import java.util.List;

public interface NominaQueryJPARepository extends JpaRepository<NominaJPAModel, Integer>, DocentePorPeriodoJPAQuery {
    @Query("SELECT n FROM ReporteNomina n WHERE n.idPeriodo = :idPeriodo ORDER BY n.id ASC")
    List<NominaJPAModel> nominasPorIdPeriodo(@Param("idPeriodo") Integer idPeriodo);

    @Query("""
            SELECT det FROM ReporteNomina n
            JOIN n.detalles det
            JOIN FETCH det.materia mat
            JOIN FETCH det.tipoDocente
            WHERE n.id = :idNomina AND det.docente.id = :idDocente
            ORDER BY mat.descripcion ASC
            """)
    List<NominaDetalleJPAModel> materiasPorDocenteNomina(@Param("idNomina") Integer idNomina, @Param("idDocente") Integer idDocente);
}
