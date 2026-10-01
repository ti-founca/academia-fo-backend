package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.contract.NominaResumenQueryRepository;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.dto.info.NominaResumenInfo;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.dto.info.PeriodoLectivoInfo;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.dto.info.PlanCurricularInfo;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.repository.NominaResumenQueryJPARepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NominaResumenQueryRepositoryImpl implements NominaResumenQueryRepository {
    private final NominaResumenQueryJPARepository jpaRepository;

    @Override
    public List<NominaResumenInfo> consultarPorPeriodoLectivo(Integer periodoLectivoId) {
        return this.jpaRepository.consultarPorPeriodoLectivo(periodoLectivoId).stream()
                .map(p -> new NominaResumenInfo(
                        p.id(),
                        p.nombre(),
                        new PeriodoLectivoInfo(p.periodoLectivoId(), p.periodoLectivoDescripcion()),
                        new PlanCurricularInfo(p.planCurricularId(), p.planCurricularDescripcion())
                ))
                .toList();
    }
}
