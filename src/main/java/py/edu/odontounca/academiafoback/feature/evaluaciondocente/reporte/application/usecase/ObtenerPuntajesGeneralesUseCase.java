package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.*;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper.EstamentoResultMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper.MateriaResultMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper.PersonaResultMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper.TipoDocenteResultMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.repository.EvaluacionQueryRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Estamento;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository.EstamentoRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository.MateriaRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository.PersonaRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository.TipoDocenteRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ObtenerPuntajesGeneralesUseCase {
    private final EstamentoRepository estamentoRepository;
    private final PersonaRepository personaRepository;
    private final MateriaRepository materiaRepository;
    private final TipoDocenteRepository tipoDocenteRepository;
    private final EvaluacionQueryRepository evaluacionQueryRepository;

    private final PersonaResultMapper personaResultMapper;
    private final MateriaResultMapper materiaResultMapper;
    private final TipoDocenteResultMapper tipoDocenteResultMapper;
    private final EstamentoResultMapper estamentoResultMapper;

    public Result execute(Query query){
        List<EvaluadoMateriaGeneralInfo> evaluadoMateriaList = this.evaluacionQueryRepository.consultarEvaluadosPorPeriodo(query.idPeriodo);
        List<Estamento> estamentoList = this.estamentoRepository.consultarTodos();
        BigDecimal porcentajeTotal = new BigDecimal("0");
        for(Estamento e : estamentoList) porcentajeTotal = porcentajeTotal.add(e.getPorcentajePeso());
        if(!(porcentajeTotal.compareTo(new BigDecimal("100.0")) == 0)){
            throw new RuntimeException("El porcentaje total de los estamentos debe sumar 100%, ahora suma " + porcentajeTotal);
        }
        List<EvaluacionGeneralDocenteMateriaInfo> listDetalles = new ArrayList<>();
        for(EvaluadoMateriaGeneralInfo evaluadoMateria : evaluadoMateriaList){
            List<DetalleEvaluacionGeneralDocenteMateriaInfo> detalleEval = new ArrayList<>();
            for(Estamento estamento: estamentoList){
                BigDecimal promedio = BigDecimal.valueOf(
                    evaluacionQueryRepository.obtenerPromedioPor(
                    query.idPeriodo,
                    evaluadoMateria.idEvaluado(),
                    evaluadoMateria.idMateria(),
                    evaluadoMateria.idTipoDocente(),
                    estamento.getId())
                );
                BigDecimal ponderacion = estamento.getPorcentajePeso().divide(new BigDecimal("100.0"));
                BigDecimal promediPonderado = promedio.multiply(ponderacion);
                detalleEval.add(new DetalleEvaluacionGeneralDocenteMateriaInfo(
                        this.estamentoResultMapper.toInfo(estamento),
                        promedio,
                        promediPonderado
                ));
            }
            BigDecimal promedioGeneral = new BigDecimal("0.0");
            for(DetalleEvaluacionGeneralDocenteMateriaInfo d : detalleEval) promedioGeneral = promedioGeneral.add(d.promedioPonderado());

            listDetalles.add(new EvaluacionGeneralDocenteMateriaInfo(
                    this.findEvaluado(evaluadoMateria.idEvaluado()),
                    this.findMateria(evaluadoMateria.idMateria()),
                    this.findTipoDocente(evaluadoMateria.idTipoDocente()),
                    promedioGeneral,
                    detalleEval
            ));
        }
        return new Result(listDetalles);
    }

    private PersonaInfo findEvaluado(Integer id){
        return this.personaRepository
                .buscarPorId(id)
                .map(this.personaResultMapper::toInfo)
                .orElseThrow(() -> new NotFoundError("Evaluado", id));
    }

    private MateriaInfo findMateria(Integer id){
        return this.materiaRepository
                .buscarPorId(id)
                .map(this.materiaResultMapper::toInfo)
                .orElseThrow(() -> new NotFoundError("Materia", id));
    }

    private TipoDocenteInfo findTipoDocente(Integer id){
        return this.tipoDocenteRepository
                .buscarPorId(id)
                .map(this.tipoDocenteResultMapper::toInfo)
                .orElseThrow(() -> new NotFoundError("TipoDocente", id));
    }

    public static record Query(Integer idPeriodo){}
    public static record Result(List<EvaluacionGeneralDocenteMateriaInfo> detalles){}

}
