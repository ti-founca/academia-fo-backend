package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract.ExcelGenerator;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.EvaluacionGeneralDocenteMateriaInfo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CrearExcelPuntajesGeneralesUseCase {
    private final ExcelGenerator excelGenerator;

    public Result execute(Query query){
        return new Result(excelGenerator.generate(query.detalles));
    }

    public static record Result(byte[] excel){}
    public static record Query(List<EvaluacionGeneralDocenteMateriaInfo> detalles){}
}
