package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.model.PeriodoLectivo;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.repository.PeriodoLectivoRepository;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.mapper.NominaPeriodoLectivoJPAMapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.repository.NominaPeriodoLectivoJPARepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NominaPeriodoLectivoRepositoryImpl implements PeriodoLectivoRepository {
    private final NominaPeriodoLectivoJPARepository jpaRepository;
    private final NominaPeriodoLectivoJPAMapper mapper;

    @Override
    public Optional<PeriodoLectivo> findById(Integer id) {
        return this.jpaRepository.findById(id).map(this.mapper::toDomain);
    }
}
