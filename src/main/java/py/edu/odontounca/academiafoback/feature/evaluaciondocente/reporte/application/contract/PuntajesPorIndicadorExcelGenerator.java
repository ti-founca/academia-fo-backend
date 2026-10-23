package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.HojaPuntajesIndicadorInfo;

import java.util.List;

public interface PuntajesPorIndicadorExcelGenerator {
    byte[] generate(List<HojaPuntajesIndicadorInfo> hojas);
}
