package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto;

import java.util.List;

public record PuntajeMateriaDTO(
        MateriaDTO materia,
        TipoDocenteDTO tipoDocente,
        List<PuntajeEstamentoDTO> puntajePorEstamento,
        String puntajeGeneral
) { }
