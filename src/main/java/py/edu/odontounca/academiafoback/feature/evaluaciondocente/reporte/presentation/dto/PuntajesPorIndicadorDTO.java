package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class PuntajesPorIndicadorDTO {
    private EstamentoDTO estamento;
    private TipoDocenteDTO tipoDocente;
    private IndicadorDTO indicador;
    private double promedio;
    private int cantidad;
}
