package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.service.ExportarPuntajesGeneralesExcelService;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.BuscarEvaluacionPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.ObtenerPuntajesGeneralesUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.EvaluacionDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.EvaluacionGeneralDocenteMateriaDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper.EvaluacionGeneralDocenteMateriaResponseMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper.EvaluacionResponseMapper;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones")
@RequiredArgsConstructor
public class EvaluacionController {
    private final BuscarEvaluacionPorIdUseCase buscarEvaluacionPorIdUseCase;
    private final ObtenerPuntajesGeneralesUseCase obtenerPuntajesGeneralesUseCase;
    private final ExportarPuntajesGeneralesExcelService exportarPuntajesGeneralesExcelService;

    private final EvaluacionResponseMapper evaluacionMapper;
    private final EvaluacionGeneralDocenteMateriaResponseMapper evaluacionGeneralMapper;

    @GetMapping("{id}")
    public EvaluacionDTO buscarPorId(@PathVariable("id") Integer id){
        BuscarEvaluacionPorIdUseCase.Result result = this.buscarEvaluacionPorIdUseCase.execute(new BuscarEvaluacionPorIdUseCase.Query(id));
        return this.evaluacionMapper.toResponse(result.evaluacion());
    }

    @GetMapping("reportes/general")
    public List<EvaluacionGeneralDocenteMateriaDTO> consultarPuntajesGenerales(
            @RequestParam() Integer idPeriodo
    ){
        ObtenerPuntajesGeneralesUseCase.Result result = this.obtenerPuntajesGeneralesUseCase.execute(new ObtenerPuntajesGeneralesUseCase.Query(idPeriodo));
        return result.detalles().stream().map(this.evaluacionGeneralMapper::toResponse).toList();
    }

    @GetMapping("reportes/general/exportar")
    public ResponseEntity<byte[]> generarReporteExcel(
            @RequestParam() Integer idPeriodo
    ){
        byte[] excel = this.exportarPuntajesGeneralesExcelService.exportar(
                new ExportarPuntajesGeneralesExcelService.Query(idPeriodo)
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=puntaje-general-docentes.xlsx");
        return new ResponseEntity<>(excel, headers, HttpStatus.OK);
    }

}
