package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract.PuntajesPorCursoExcelGenerator;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.HojaPuntajesCursoInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PuntajeGeneralInfo;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CrearExcelPuntajesPorCursoUseCase {
    // Mismas pestañas que la vista «Por Curso»
    private static final Map<Integer, String> CURSOS = Map.of(
            1, "1er año",
            2, "2do año",
            3, "3er año",
            4, "4to año",
            5, "5to año"
    );
    private static final Comparator<PuntajeGeneralInfo> ORDEN = Comparator
            .comparing((PuntajeGeneralInfo p) -> p.evaluado().apellidos(), String.CASE_INSENSITIVE_ORDER)
            .thenComparing(p -> p.evaluado().nombres(), String.CASE_INSENSITIVE_ORDER)
            .thenComparing(p -> p.materia().descripcion(), String.CASE_INSENSITIVE_ORDER);

    private final PuntajesPorCursoExcelGenerator excelGenerator;

    public Result execute(Query query){
        List<HojaPuntajesCursoInfo> hojas = CURSOS.keySet().stream()
                .sorted()
                .map(anho -> new HojaPuntajesCursoInfo(
                        CURSOS.get(anho),
                        query.detalles().stream()
                                .filter(p -> anho.equals(p.materia().curso().anho()))
                                .sorted(ORDEN)
                                .toList()
                ))
                .toList();
        return new Result(this.excelGenerator.generate(hojas));
    }

    public static record Result(byte[] excel){}
    public static record Query(List<PuntajeGeneralInfo> detalles){}
}
