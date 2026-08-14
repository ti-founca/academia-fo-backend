package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PuntajeGeneralInfo;

import java.util.List;

public interface ExcelGenerator {
    byte[] generate(List<PuntajeGeneralInfo> detalles);
}
