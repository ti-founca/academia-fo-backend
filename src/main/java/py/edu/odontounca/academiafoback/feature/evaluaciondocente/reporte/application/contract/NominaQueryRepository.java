package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.MateriaTipoDocenteInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PersonaInfo;

import java.util.List;

public interface NominaQueryRepository {
    List<PersonaInfo> docentesPorPeriodo(Integer idPeriodo);
    Integer iddNominaPorPeriodo(Integer idPeriodo);
    List<MateriaTipoDocenteInfo> materiasPorDocenteNomina(Integer idNomina, Integer idDocente);
}
