package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.service.ExportarPuntajeDocentePdfService;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.ObtenerPuntajesDocentesUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.ObtenerPuntajesGeneralesUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.PuntajeDocenteDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.PuntajeDocenteMateriaDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper.PuntajeDocenteMateriaResponseMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper.PuntajeDocenteResponseMapper;

import java.util.List;

@RestController
@RequestMapping("/api/periodos-lectivos/{idPeriodo}/reportes")
@RequiredArgsConstructor
public class ReporteDocenteController {
    private final ObtenerPuntajesDocentesUseCase obtenerPuntajesDocentesUseCase;
    private final ObtenerPuntajesGeneralesUseCase obtenerPuntajesGeneralesUseCase;
    private final ExportarPuntajeDocentePdfService exportarPuntajeDocentePdfService;
    private final PuntajeDocenteResponseMapper puntajeDocenteResponseMapper;
    private final PuntajeDocenteMateriaResponseMapper puntajeDocenteMateriaResponseMapper;

    @GetMapping("docentes")
    public List<PuntajeDocenteDTO> consultarPuntajesDocentes(@PathVariable Integer idPeriodo){
        ObtenerPuntajesDocentesUseCase.Result result = this.obtenerPuntajesDocentesUseCase.execute(
                new ObtenerPuntajesDocentesUseCase.Query(idPeriodo, null)
        );
        return this.puntajeDocenteResponseMapper.toResponseList(result.puntajes());
    }

    @GetMapping("docentes/{idDocente}")
    public PuntajeDocenteDTO consultarPuntajesDocente(
            @PathVariable Integer idPeriodo,
            @PathVariable Integer idDocente
    ){
        ObtenerPuntajesDocentesUseCase.Result result = this.obtenerPuntajesDocentesUseCase.execute(
                new ObtenerPuntajesDocentesUseCase.Query(idPeriodo, idDocente)
        );
        return this.puntajeDocenteResponseMapper.toResponse(result.puntajes().getFirst());
    }

    @GetMapping("docentes/{idDocente}/pdf")
    public ResponseEntity<byte[]> exportarPdfPuntajesDocente(
            @PathVariable Integer idPeriodo,
            @PathVariable Integer idDocente
    ){
        byte[] pdf = this.exportarPuntajeDocentePdfService.exportar(
                new ExportarPuntajeDocentePdfService.Query(idPeriodo, idDocente)
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=puntaje-docente-" + idDocente + ".pdf");
        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }

    @GetMapping("docentes-materias")
    public List<PuntajeDocenteMateriaDTO> consultarPuntajesDocentesMaterias(@PathVariable Integer idPeriodo){
        ObtenerPuntajesGeneralesUseCase.Result result = this.obtenerPuntajesGeneralesUseCase.execute(
                new ObtenerPuntajesGeneralesUseCase.Query(idPeriodo)
        );
        return this.puntajeDocenteMateriaResponseMapper.toResponseList(result.detalles());
    }
}
