package py.edu.odontounca.academiafoback.feature.nominadocente.application.contract;

import py.edu.odontounca.academiafoback.feature.nominadocente.application.dto.info.NominaResumenInfo;

import java.util.List;

public interface NominaResumenQueryRepository {
    List<NominaResumenInfo> consultarPorPeriodoLectivo(Integer periodoLectivoId);
}
