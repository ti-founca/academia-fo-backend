package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.model.PlanCurricular;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.repository.PlanCurricularRepository;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.mapper.PlanCurricularJPAMapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.repository.PlanCurricularJPARepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlanCurricularRepositoryImpl implements PlanCurricularRepository {
    private final PlanCurricularJPARepository jpaRepository;
    private final PlanCurricularJPAMapper mapper;

    @Override
    public Optional<PlanCurricular> findById(Integer id) {
        return this.jpaRepository.findById(id).map(this.mapper::toDomain);
    }
}
