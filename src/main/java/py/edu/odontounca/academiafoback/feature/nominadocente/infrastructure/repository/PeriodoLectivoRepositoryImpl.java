package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.model.PeriodoLectivo;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.repository.PeriodoLectivoRepository;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.mapper.PeriodoLectivoJPAMapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.repository.PeriodoLectivoJPARepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PeriodoLectivoRepositoryImpl implements PeriodoLectivoRepository {
    private final PeriodoLectivoJPARepository jpaRepository;
    private final PeriodoLectivoJPAMapper mapper;

    @Override
    public Optional<PeriodoLectivo> findById(Integer id) {
        return this.jpaRepository.findById(id).map(this.mapper::toDomain);
    }
}
