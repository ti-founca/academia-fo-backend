package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.DetalleEvaluacion;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.repository.DetalleEvaluacionRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.mapper.DetalleEvaluacionJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.repository.DetalleEvaluacionJPARepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DetalleEvaluacionRepositoryImpl implements DetalleEvaluacionRepository {

    private final DetalleEvaluacionJPARepository jpaRepository;
    private final DetalleEvaluacionJPAMapper mapper;

    @Override
    public Optional<DetalleEvaluacion> buscarPorId(Integer id) {
        return this.jpaRepository.findById(id).map(mapper::toDomain);
    }
}
