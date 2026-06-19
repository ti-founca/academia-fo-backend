package py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class DetalleEvaluacionDTO {
    private Integer id;
    private Integer puntaje;
    private DetalleEvaluacionDTO.Indicador indicador;

    @AllArgsConstructor @Getter @Setter
    public static class Indicador {
        Integer id;
        String descirpcion;
    }
}
