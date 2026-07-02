package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DetalleEvaluacionGeneralDocenteMateriaDTO {
    private String estamento;
    private String peso;
    private double promedio;
    private double promedioPonderado;
}
