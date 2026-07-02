package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Evaluacion;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository.EvaluacionRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper.EvaluacionJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.repository.EvaluacionJPARepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EvaluacionRepositoryImpl implements EvaluacionRepository {
    private final EvaluacionJPARepository jpaRepository;
    private final EvaluacionJPAMapper jpaMapper;

    @Override
    public Optional<Evaluacion> buscarPorId(Integer id) {
        return this.jpaRepository.findById(id).map(this.jpaMapper::toDomain);
    }
}
