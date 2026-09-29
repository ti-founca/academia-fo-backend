package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto;

import java.util.List;

public record PuntajeDocenteDTO(
        Integer idDocente,
        String nombres,
        String apellidos,
        String ci,
        List<PuntajeMateriaDTO> puntajes
) { }
