package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract.ReportePuntajeDocentePdfGenerator;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PuntajeDocenteInfo;

@Service
@RequiredArgsConstructor
public class CrearPdfPuntajeDocenteUseCase {
    private final ReportePuntajeDocentePdfGenerator pdfGenerator;

    public Result execute(Query query){
        return new Result(this.pdfGenerator.generate(query.docente(), query.periodo()));
    }

    public static record Query(PuntajeDocenteInfo docente, String periodo){}
    public static record Result(byte[] pdf){}
}
