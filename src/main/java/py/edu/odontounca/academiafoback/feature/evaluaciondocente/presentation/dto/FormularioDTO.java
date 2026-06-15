package py.edu.odontounca.academiafoback.feature.evaluaciondocente.presentation.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter @Setter @RequiredArgsConstructor
public class FormularioDTO {
    private Integer id;
    private String descripcion;
    private Instant fechaApertura;
    private Instant fechaCierre;
    private boolean activo;
    private List<IndicadorDTO> indicadores;
}
