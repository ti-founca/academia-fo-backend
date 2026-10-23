package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PuntajeDocenteInfo;

public interface ReportePuntajeDocentePdfGenerator {
    byte[] generate(PuntajeDocenteInfo docente, String periodo);
}
