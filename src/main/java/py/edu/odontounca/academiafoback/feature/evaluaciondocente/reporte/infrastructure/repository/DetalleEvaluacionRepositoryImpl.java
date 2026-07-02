package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.DetalleEvaluacion;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository.DetalleEvaluacionRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper.DetalleEvaluacionJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.repository.DetalleEvaluacionJPARepository;

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
