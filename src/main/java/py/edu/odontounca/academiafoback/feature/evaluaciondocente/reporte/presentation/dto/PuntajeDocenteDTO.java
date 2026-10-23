package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto;

import java.util.List;

public record PuntajeDocenteDTO(
        Integer idDocente,
        String nombres,
        String apellidos,
        String ci,
        List<PuntajeMateria> puntajes
) {
    public static record PuntajeMateria(
        MateriaDTO materia,
        TipoDocenteDTO tipoDocente,
        List<PuntajeEstamento> puntajePorEstamento,
        String puntajeGeneral
    ){ }
    public static record PuntajeEstamento(
        EstamentoDTO estamento,
        String puntaje,
        String puntajePonderado,
        List<PuntajeIndicador> puntajePorIndicador
    ){ }
    public static record PuntajeIndicador(
        IndicadorDTO indicador,
        String puntaje,
        Integer cantidad
    ){ }
}
