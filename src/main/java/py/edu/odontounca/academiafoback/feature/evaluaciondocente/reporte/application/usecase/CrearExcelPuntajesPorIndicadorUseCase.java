package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract.PuntajesPorIndicadorExcelGenerator;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.HojaPuntajesIndicadorInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PuntajeIndicadorInfo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CrearExcelPuntajesPorIndicadorUseCase {
    private final PuntajesPorIndicadorExcelGenerator excelGenerator;

    public Result execute(Query query){
        return new Result(this.excelGenerator.generate(this.agruparPorHoja(query.detalles())));
    }

    // Una hoja por estamento + tipo de docente. Los promedios se ponderan por cantidad porque,
    // al filtrar por tipo de docente, la consulta devuelve una fila por evaluado para cada indicador.
    private List<HojaPuntajesIndicadorInfo> agruparPorHoja(List<PuntajeIndicadorInfo> detalles){
        Map<List<Integer>, List<PuntajeIndicadorInfo>> porHoja = new LinkedHashMap<>();
        detalles.stream()
                .sorted(Comparator.comparing((PuntajeIndicadorInfo d) -> d.estamento().id())
                        .thenComparing(d -> d.tipoDocente().id()))
                .forEach(d -> porHoja
                        .computeIfAbsent(List.of(d.estamento().id(), d.tipoDocente().id()), k -> new ArrayList<>())
                        .add(d));

        return porHoja.values().stream().map(filas -> {
            Map<Integer, List<PuntajeIndicadorInfo>> porIndicador = new LinkedHashMap<>();
            filas.forEach(f -> porIndicador.computeIfAbsent(f.indicador().id(), k -> new ArrayList<>()).add(f));
            List<PuntajeIndicadorInfo> indicadores = porIndicador.values().stream()
                    .map(this::combinar)
                    .sorted(Comparator.comparing(i -> i.indicador().nroOrden()))
                    .toList();
            PuntajeIndicadorInfo primera = filas.getFirst();
            return new HojaPuntajesIndicadorInfo(
                    primera.estamento(),
                    primera.tipoDocente(),
                    indicadores,
                    this.promedioPonderado(indicadores)
            );
        }).toList();
    }

    private PuntajeIndicadorInfo combinar(List<PuntajeIndicadorInfo> filas){
        PuntajeIndicadorInfo primera = filas.getFirst();
        return new PuntajeIndicadorInfo(
                primera.estamento(),
                primera.tipoDocente(),
                primera.indicador(),
                this.promedioPonderado(filas),
                filas.stream().mapToInt(PuntajeIndicadorInfo::cantidad).sum()
        );
    }

    private BigDecimal promedioPonderado(List<PuntajeIndicadorInfo> filas){
        BigDecimal suma = BigDecimal.ZERO;
        long cantidad = 0;
        for(PuntajeIndicadorInfo f : filas){
            suma = suma.add(f.puntaje().multiply(BigDecimal.valueOf(f.cantidad())));
            cantidad += f.cantidad();
        }
        if(cantidad == 0) return BigDecimal.ZERO;
        return suma.divide(BigDecimal.valueOf(cantidad), 2, RoundingMode.HALF_UP);
    }

    public static record Result(byte[] excel){}
    public static record Query(List<PuntajeIndicadorInfo> detalles){}
}
