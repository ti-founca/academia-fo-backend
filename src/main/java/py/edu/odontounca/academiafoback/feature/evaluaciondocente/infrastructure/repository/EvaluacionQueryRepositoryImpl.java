package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.info.EvaluadoMateriaGeneralInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.repository.EvaluacionQueryRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.repository.EvaluacionQueryJPARepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EvaluacionQueryRepositoryImpl implements EvaluacionQueryRepository {
    private final EvaluacionQueryJPARepository evaluacionQueryJPARepository;

    @Override
    public List<EvaluadoMateriaGeneralInfo> consultarEvaluadosPorPeriodo(Integer idPeriodo) {
        return this.evaluacionQueryJPARepository.consultarEvaluadosPorPeriodoTipo(idPeriodo);
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
}
