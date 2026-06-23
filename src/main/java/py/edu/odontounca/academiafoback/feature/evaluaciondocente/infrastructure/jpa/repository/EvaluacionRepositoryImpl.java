package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Evaluacion;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.repository.EvaluacionRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.mapper.EvaluacionJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.mapper.EvaluacionResponseMapper;

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
