package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PuntajeGeneralInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.CrearExcelPuntajesPorCursoUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.ObtenerPuntajesGeneralesUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExportarPuntajesPorCursoExcelService {
    private final ObtenerPuntajesGeneralesUseCase obtenerPuntajesGeneralesUseCase;
    private final CrearExcelPuntajesPorCursoUseCase crearExcelPuntajesPorCursoUseCase;

    public byte[] exportar(Query query){
        ObtenerPuntajesGeneralesUseCase.Result puntajesResult = this.obtenerPuntajesGeneralesUseCase.execute(
                new ObtenerPuntajesGeneralesUseCase.Query(query.idPeriodo())
        );
        List<PuntajeGeneralInfo> detalles = puntajesResult.detalles().stream()
                .filter(p -> query.idTipoDocente() == null || query.idTipoDocente().equals(p.tipoDocente().id()))
                .toList();
        CrearExcelPuntajesPorCursoUseCase.Result excelResult = this.crearExcelPuntajesPorCursoUseCase.execute(
                new CrearExcelPuntajesPorCursoUseCase.Query(detalles)
        );
        return excelResult.excel();
    }

    public static record Query(Integer idPeriodo, Integer idTipoDocente){}
}
