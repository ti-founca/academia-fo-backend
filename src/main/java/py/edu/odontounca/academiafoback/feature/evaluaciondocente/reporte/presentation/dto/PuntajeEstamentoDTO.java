package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class PuntajeEstamentoDTO {
    private Integer idEstamento;
    private String estamento;
    private String ponderacion;
    private String puntaje;
    private String puntajePonderado;
}
