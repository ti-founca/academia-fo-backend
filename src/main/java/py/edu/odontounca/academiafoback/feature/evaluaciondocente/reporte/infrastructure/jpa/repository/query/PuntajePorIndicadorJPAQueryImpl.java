package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.repository.query;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.dto.PuntajeIndicadorProjection;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.DetalleEvaluacionJPAModel;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.EstamentoJPAModel;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.IndicadorJPAModel;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.TipoDocenteJPAModel;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PuntajePorIndicadorJPAQueryImpl implements PuntajePorIndicadorJPAQuery {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PuntajeIndicadorProjection> obtenerPuntajeIndicador(
            Integer idPeriodo,
            Integer idEstamento,
            Integer idTipoDocente,
            Integer idEvaluado,
            Integer idMateria
    ) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery query = cb.createQuery(PuntajeIndicadorProjection.class);
        Root<DetalleEvaluacionJPAModel> root = query.from(DetalleEvaluacionJPAModel.class);

        Path<IndicadorJPAModel> indicadorPath = root.get("indicador");
        Path<TipoDocenteJPAModel> tipoDocentePath = root.get("evaluacion").get("formulario").get("tipoEvaluacion").get("tipoDocente");
        Path<Integer> idTipoDocentePath = tipoDocentePath.get("id");
        Path<EstamentoJPAModel> estamentoPath = root.get("evaluacion").get("formulario").get("tipoEvaluacion").get("estamento");
        Path<Integer> idEstamentoPath = estamentoPath.get("id");
        Path<Boolean> terminadoPath = root.get("evaluacion").get("terminado");
        Path<Integer> idPeriodoPath = root.get("evaluacion").get("formulario").get("idPeriodo");
        Path<Integer> idEvaluadoPath = root.get("evaluacion").get("evaluado").get("id");
        Path<Double> puntajePath = root.get("puntaje");
        Path<Integer> idMateriaPath = root.get("evaluacion").get("materia").get("id");

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(idPeriodoPath, idPeriodo));
        predicates.add(cb.isTrue(terminadoPath));

        //Filtros opcionales
        if (idTipoDocente != null) predicates.add(cb.equal(idTipoDocentePath, idTipoDocente));
        if (idEstamento != null) predicates.add(cb.equal(idEstamentoPath, idEstamento));
        if (idEvaluado != null) predicates.add(cb.equal(idEvaluadoPath, idEvaluado));
        if (idMateria != null) predicates.add(cb.equal(idMateriaPath, idMateria));

        query.where(predicates.toArray(new Predicate[0]));
        query.select(cb.construct(
                PuntajeIndicadorProjection.class,
                indicadorPath,
                tipoDocentePath,
                estamentoPath,
                cb.avg(puntajePath),
                cb.count(root)
                )
        );
        if(idTipoDocente != null)
            query.groupBy(indicadorPath, tipoDocentePath, estamentoPath, idEvaluadoPath);
        else
            query.groupBy(indicadorPath, tipoDocentePath, estamentoPath);
        return entityManager.createQuery(query).getResultList();
    }
}
