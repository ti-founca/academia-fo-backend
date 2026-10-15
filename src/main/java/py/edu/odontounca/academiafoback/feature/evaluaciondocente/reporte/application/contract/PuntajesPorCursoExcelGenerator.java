package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.HojaPuntajesCursoInfo;

import java.util.List;

public interface PuntajesPorCursoExcelGenerator {
    byte[] generate(List<HojaPuntajesCursoInfo> hojas);
}
