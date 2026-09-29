package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.repository.query;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper.PersonaJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.NominaJPAModel;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.PersonaJPAModel;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DocentePorPeriodoJPAQueryImpl implements DocentePorPeriodoJPAQuery {
    @PersistenceContext
    private EntityManager entityManager;

    private final PersonaJPAMapper personaJPAMapper;

    @Override
    public List<PersonaJPAModel> obtenerDocentesPorPeriodo(Integer idPeriodo) {
        NominaJPAModel nomina = entityManager.createQuery(
                        "SELECT n FROM ReporteNomina n WHERE n.idPeriodo = :idPeriodo", NominaJPAModel.class
                ).setParameter("idPeriodo", idPeriodo)
                .getSingleResultOrNull();

        if (nomina == null) return List.of();

        List<PersonaJPAModel> docentesList = entityManager.createQuery("""
                SELECT DISTINCT doc FROM ReporteNomina n
                JOIN n.detalles det
                JOIN det.docente doc
                WHERE n.id = :idNomina
                ORDER BY doc.apellidos, doc.nombres
        """, PersonaJPAModel.class
                ).setParameter("idNomina", nomina.getId())
                .getResultList();
        return docentesList;
    }

}
