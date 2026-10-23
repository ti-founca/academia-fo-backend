package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto;

import java.util.List;

public record PuntajeDocenteMateriaDTO(
        PersonaDTO docente,
        MateriaDTO materia,
        TipoDocenteDTO tipoDocente,
        String puntajeGeneral,
        List<PuntajeEstamento> puntajePorEstamento
) {
    public static record PuntajeEstamento(
            EstamentoDTO estamento,
            String puntaje,
            String puntajePonderado
    ){ }
}
