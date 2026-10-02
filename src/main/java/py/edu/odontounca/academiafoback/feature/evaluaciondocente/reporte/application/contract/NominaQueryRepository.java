package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.MateriaTipoDocenteInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PersonaInfo;

import java.util.List;

public interface NominaQueryRepository {
    List<PersonaInfo> docentesPorPeriodo(Integer idPeriodo);
    boolean existeNominaPorPeriodo(Integer idPeriodo);
    List<MateriaTipoDocenteInfo> materiasPorDocentePeriodo(Integer idPeriodo, Integer idDocente);
}
