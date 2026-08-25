package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.EvaluadoInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract.EvaluacionQueryRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PuntajeIndicadorInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper.EstamentoJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper.IndicadorJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper.TipoDocenteJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.repository.EvaluacionQueryJPARepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EvaluacionQueryRepositoryImpl implements EvaluacionQueryRepository {
    private final EvaluacionQueryJPARepository evaluacionQueryJPARepository;

    private final EstamentoJPAMapper estamentoMapper;
    private final TipoDocenteJPAMapper tipoDocenteMapper;
    private final IndicadorJPAMapper indicadorMapper;

    @Override
    public List<EvaluadoInfo> consultarEvaluadosPor(Integer idPeriodo) {
        return this.evaluacionQueryJPARepository.consultarEvaluadosPor(idPeriodo);
    }

    @Override
    public List<EvaluadoInfo> consultarEvaluadosPor(Integer idPeriodo, Integer idEstamento) {
        return this.evaluacionQueryJPARepository.consultarEvaluadosPor(idPeriodo, idEstamento);
    }

    @Override
    public Double obtenerPromedioPor(Integer idPeriodo, Integer idEvaluado, Integer idMateria, Integer idTipoDocente, Integer idEstamento) {
        return this.evaluacionQueryJPARepository
                .obtenerPromedioPor(idPeriodo, idEvaluado, idMateria, idTipoDocente, idEstamento)
                .orElse(0.0);
    }

    @Override
    public Double obtenerCantidadPromediadaPor(Integer idPeriodo, Integer idEvaluado, Integer idMateria, Integer idTipoDocente) {
        return this.evaluacionQueryJPARepository.obtenerCantidadPromediadadPor(idPeriodo, idEvaluado, idMateria, idTipoDocente);
    }

    @Override
    public List<PuntajeIndicadorInfo> obtenerPuntajeIndicadorPor(Integer idPeriodo, Integer idEstamento, Integer idTipoDocente) {
        return this.evaluacionQueryJPARepository.obtenerPuntajeIndicador(idPeriodo, idEstamento, idTipoDocente)
            .stream()
            .map(puntajeProj -> new PuntajeIndicadorInfo(
                    this.estamentoMapper.toInfo(puntajeProj.estamento()),
                    this.tipoDocenteMapper.toInfo(puntajeProj.tipoDocente()),
                    this.indicadorMapper.toInfo(puntajeProj.indicador()),
                    new BigDecimal(puntajeProj.promedio() + "").setScale(2, RoundingMode.HALF_UP),
                    puntajeProj.cantidad().intValue()
            ))
            .toList();
    }
}
