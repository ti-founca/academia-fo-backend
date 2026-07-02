package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter @Setter
public class EvaluacionDTO {
    private Integer id;
    private Instant fechaInicio;
    private Instant fechaFin;
    private List<DetalleEvaluacionDTO> detalles;
}
