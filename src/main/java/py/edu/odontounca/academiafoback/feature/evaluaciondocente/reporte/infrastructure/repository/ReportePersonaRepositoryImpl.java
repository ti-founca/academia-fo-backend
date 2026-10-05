package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Persona;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository.ReportePersonaRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper.ReportePersonaJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.repository.ReportePersonaJPARepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReportePersonaRepositoryImpl implements ReportePersonaRepository {
    private final ReportePersonaJPARepository jpaRepository;
    private final ReportePersonaJPAMapper mapper;

    @Override
    public Optional<Persona> buscarPorId(Integer id) {
        return this.jpaRepository.findById(id).map(this.mapper::toDomain);
    }
}
