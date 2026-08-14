package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.CrearExcelPuntajesGeneralesUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.ObtenerPuntajesGeneralesUseCase;

@Service
@RequiredArgsConstructor
public class ExportarPuntajesGeneralesExcelService {
    private final ObtenerPuntajesGeneralesUseCase obtenerPuntajesGeneralesUseCase;
    private final CrearExcelPuntajesGeneralesUseCase crearExcelPuntajesGeneralesUseCase;

    public byte[] exportar(Query query){
        ObtenerPuntajesGeneralesUseCase.Query obtenerPuntajesQuery =
                new ObtenerPuntajesGeneralesUseCase.Query(query.idPeriodo());
        ObtenerPuntajesGeneralesUseCase.Result puntajesResult =
                obtenerPuntajesGeneralesUseCase.execute(obtenerPuntajesQuery);
        CrearExcelPuntajesGeneralesUseCase.Query crearExcelQuery =
                new CrearExcelPuntajesGeneralesUseCase.Query(puntajesResult.detalles());
        CrearExcelPuntajesGeneralesUseCase.Result excelResult =
                crearExcelPuntajesGeneralesUseCase.execute(crearExcelQuery);
        return excelResult.excel();
    }

    public static record Query(Integer idPeriodo){}
}
