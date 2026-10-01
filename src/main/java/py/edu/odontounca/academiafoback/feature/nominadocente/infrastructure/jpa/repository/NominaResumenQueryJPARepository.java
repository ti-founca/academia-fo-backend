package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.dto.NominaResumenProjection;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.model.NominaJPAModel;

import java.util.List;

public interface NominaResumenQueryJPARepository extends JpaRepository<NominaJPAModel, Integer> {
    @Query("""
            SELECT new py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.dto.NominaResumenProjection(
                n.id, n.nombre, per.id, per.nombre, plan.id, plan.nombre
            )
            FROM NominaJPAModel n
            JOIN n.periodo per
            JOIN n.planCurricularJPAModel plan
            WHERE per.id = :periodoLectivoId
            ORDER BY n.nombre
            """)
    List<NominaResumenProjection> consultarPorPeriodoLectivo(@Param("periodoLectivoId") Integer periodoLectivoId);
}
