package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.NominaDetalleJPAModel;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.NominaJPAModel;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.PersonaJPAModel;

import java.util.List;

public interface NominaQueryJPARepository extends JpaRepository<NominaJPAModel, Integer> {
    boolean existsByIdPeriodo(Integer idPeriodo);

    @Query("""
            SELECT DISTINCT doc FROM ReporteNomina n
            JOIN n.detalles det
            JOIN det.docente doc
            WHERE n.idPeriodo = :idPeriodo
            ORDER BY doc.apellidos, doc.nombres
            """)
    List<PersonaJPAModel> docentesPorPeriodo(@Param("idPeriodo") Integer idPeriodo);

    @Query("""
            SELECT det FROM ReporteNomina n
            JOIN n.detalles det
            JOIN FETCH det.materia mat
            JOIN FETCH det.tipoDocente
            WHERE n.idPeriodo = :idPeriodo AND det.docente.id = :idDocente
            ORDER BY mat.descripcion ASC
            """)
    List<NominaDetalleJPAModel> materiasPorDocentePeriodo(@Param("idPeriodo") Integer idPeriodo, @Param("idDocente") Integer idDocente);
}
