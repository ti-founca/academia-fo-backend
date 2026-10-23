package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.CrearExcelPuntajesPorIndicadorUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.ObtenerPuntajesPorIndicadorUseCase;

@Service
@RequiredArgsConstructor
public class ExportarPuntajesPorIndicadorExcelService {
    private final ObtenerPuntajesPorIndicadorUseCase obtenerPuntajesPorIndicadorUseCase;
    private final CrearExcelPuntajesPorIndicadorUseCase crearExcelPuntajesPorIndicadorUseCase;

    public byte[] exportar(Query query){
        ObtenerPuntajesPorIndicadorUseCase.Result puntajesResult = this.obtenerPuntajesPorIndicadorUseCase.execute(
                new ObtenerPuntajesPorIndicadorUseCase.Query(query.idPeriodo(), null, query.idTipoDocente(), null, null)
        );
        CrearExcelPuntajesPorIndicadorUseCase.Result excelResult = this.crearExcelPuntajesPorIndicadorUseCase.execute(
                new CrearExcelPuntajesPorIndicadorUseCase.Query(puntajesResult.detalles())
        );
        return excelResult.excel();
    }

    public static record Query(Integer idPeriodo, Integer idTipoDocente){}
}
