package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info;

import java.util.List;

public record PuntajeDocenteInfo(
        Integer id,
        String nombres,
        String apellidos,
        String ci,
        List<PuntajeMateriaInfo> puntajeMateriaList
) { }
