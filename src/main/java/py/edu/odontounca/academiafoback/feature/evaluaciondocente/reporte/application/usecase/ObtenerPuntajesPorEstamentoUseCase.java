package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract.EvaluacionQueryRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.*;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper.EstamentoResultMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper.MateriaResultMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper.PersonaResultMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper.TipoDocenteResultMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository.EstamentoRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository.MateriaRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository.PersonaRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository.TipoDocenteRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ObtenerPuntajesPorEstamentoUseCase {
    private final EvaluacionQueryRepository evaluacionQueryRepository;
    private final PersonaRepository personaRepository;
    private final MateriaRepository materiaRepository;
    private final TipoDocenteRepository tipoDocenteRepository;
    private final EstamentoRepository estamentoRepository;

    private final PersonaResultMapper personaMapper;
    private final MateriaResultMapper materiaMapper;
    private final TipoDocenteResultMapper tipoDocenteMapper;
    private final EstamentoResultMapper estamentoMapper;

    public Result execute(Query query) {
        EstamentoInfo estamentoInfo = this.findEstamento(query.idEstamento());
        List<PuntajeGeneralInfo> puntajeGeneralList = new ArrayList<>();
        List<EvaluadoInfo> evaluadoInfoList = this.evaluacionQueryRepository.consultarEvaluadosPor(query.idPeriodo, query.idEstamento);
        for(EvaluadoInfo evaluadoInfo : evaluadoInfoList){
            BigDecimal puntaje = BigDecimal.valueOf(
                    this.evaluacionQueryRepository.obtenerPromedioPor(
                        query.idPeriodo(), evaluadoInfo.idEvaluado(), evaluadoInfo.idMateria(), evaluadoInfo.idTipoDocente(), query.idEstamento()
                    )
            );
            puntajeGeneralList.add(
                new PuntajeGeneralInfo(
                        this.findEvaluado(evaluadoInfo.idEvaluado()),
                        this.findMateria(evaluadoInfo.idMateria()),
                        this.findTipoDocente(evaluadoInfo.idTipoDocente()),
                        puntaje,
                        List.of()
                )
            );

        }
        return new Result(estamentoInfo, puntajeGeneralList);
    }

    private PersonaInfo findEvaluado(Integer id){
        return this.personaRepository
                .buscarPorId(id)
                .map(this.personaMapper::toInfo)
                .orElseThrow(() -> new NotFoundError("Evaluado", id));
    }
    private MateriaInfo findMateria(Integer id){
        return this.materiaRepository
                .buscarPorId(id)
                .map(this.materiaMapper::toInfo)
                .orElseThrow(() -> new NotFoundError("Materia", id));
    }
    private TipoDocenteInfo findTipoDocente(Integer id){
        return this.tipoDocenteRepository
                .buscarPorId(id)
                .map(this.tipoDocenteMapper::toInfo)
                .orElseThrow(() -> new NotFoundError("TipoDocente", id));
    }
    private EstamentoInfo findEstamento(Integer id){
        return this.estamentoRepository
                .buscarPorId(id)
                .map(this.estamentoMapper::toInfo)
                .orElseThrow(() -> new NotFoundError("Estamento", id));
    }
    public static record Query(Integer idPeriodo, Integer idEstamento) { }
    public static record Result(EstamentoInfo estamento, List<PuntajeGeneralInfo> detalles) {}
}
