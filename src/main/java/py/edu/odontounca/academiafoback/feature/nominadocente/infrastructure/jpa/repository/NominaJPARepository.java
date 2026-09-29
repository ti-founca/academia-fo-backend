package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.model.DocenteJPAModel;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.model.NominaJPAModel;

import java.util.List;
import java.util.Optional;

public interface NominaJPARepository extends JpaRepository<NominaJPAModel, Integer>, JpaSpecificationExecutor<NominaJPAModel> {
    @Query("""
            SELECT DISTINCT doc FROM NominaJPAModel n
            JOIN n.detalles det
            JOIN det.docente doc
            WHERE n.id = :nominaId
            ORDER BY doc.apellidos, doc.nombres
            """)
    List<DocenteJPAModel> findDocentesByNominaId(@Param("nominaId") Integer nominaId);

    @Query("""
            SELECT DISTINCT doc FROM NominaJPAModel n
            JOIN n.detalles det
            JOIN det.docente doc
            WHERE n.id = :nominaId AND doc.id = :docenteId
            """)
    Optional<DocenteJPAModel> findDocenteByNominaIdAndDocenteId(
            @Param("nominaId") Integer nominaId,
            @Param("docenteId") Integer docenteId
    );
}
