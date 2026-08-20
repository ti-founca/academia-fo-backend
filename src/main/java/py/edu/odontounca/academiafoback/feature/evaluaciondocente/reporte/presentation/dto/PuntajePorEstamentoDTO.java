package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor
public class PuntajePorEstamentoDTO {
    private EstamentoDTO estamento;
    private List<Puntaje> puntajes;

    @Getter @Setter @AllArgsConstructor
    public static class Puntaje {
        private String docente;
        private String materia;
        private String codigoMateria;
        private String tipoDocente;
        private double promedioGeneral;
    }
}
