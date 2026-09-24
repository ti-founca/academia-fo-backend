package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.model.Nomina;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.repository.NominaRepository;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.mapper.NominaJPAMapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.repository.NominaJPARepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NominaRepositoryImpl implements NominaRepository {
    private final NominaJPARepository jpaRepository;
    private final NominaJPAMapper mapper;

    @Override
    public Optional<Nomina> findById(Integer id) {
        return this.jpaRepository.findById(id).map(this.mapper::toDomain);
    }
}
