package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info;

import java.math.BigDecimal;
import java.util.List;

public record PuntajeDocenteInfo(
        Integer id,
        String nombres,
        String apellidos,
        String ci,
        List<PuntajeMateria> puntajeMateriaList
) {
    public static record PuntajeMateria(
            MateriaInfo materia,
            TipoDocenteInfo tipoDocente,
            BigDecimal puntajeGeneral,
            List<PuntajeEstamento> puntajePorEstamento
    ){ }
    public static record PuntajeEstamento(
        EstamentoInfo estamento,
        BigDecimal puntaje,
        BigDecimal puntajePonderado,
        List<PuntajeIndicador> puntajePorIndicador
    ){ }
    public static record PuntajeIndicador(
        IndicadorInfo indicador,
        BigDecimal puntaje,
        Integer cantidad
    ){}
}
