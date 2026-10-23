package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.CrearPdfPuntajeDocenteUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.ObtenerPuntajesDocentesUseCase;
import py.edu.odontounca.academiafoback.feature.periodolectivo.application.usecase.BuscarPeriodoLectivoPorIdUseCase;

@Service
@RequiredArgsConstructor
public class ExportarPuntajeDocentePdfService {
    private final ObtenerPuntajesDocentesUseCase obtenerPuntajesDocentesUseCase;
    private final BuscarPeriodoLectivoPorIdUseCase buscarPeriodoLectivoPorIdUseCase;
    private final CrearPdfPuntajeDocenteUseCase crearPdfPuntajeDocenteUseCase;

    public byte[] exportar(Query query){
        ObtenerPuntajesDocentesUseCase.Result puntajesResult = this.obtenerPuntajesDocentesUseCase.execute(
                new ObtenerPuntajesDocentesUseCase.Query(query.idPeriodo(), query.idDocente())
        );
        BuscarPeriodoLectivoPorIdUseCase.Result periodoResult = this.buscarPeriodoLectivoPorIdUseCase.execute(
                new BuscarPeriodoLectivoPorIdUseCase.Query(query.idPeriodo())
        );
        CrearPdfPuntajeDocenteUseCase.Result pdfResult = this.crearPdfPuntajeDocenteUseCase.execute(
                new CrearPdfPuntajeDocenteUseCase.Query(
                        puntajesResult.puntajes().getFirst(),
                        periodoResult.periodoLectivo().descripcion()
                )
        );
        return pdfResult.pdf();
    }

    public static record Query(Integer idPeriodo, Integer idDocente){}
}
