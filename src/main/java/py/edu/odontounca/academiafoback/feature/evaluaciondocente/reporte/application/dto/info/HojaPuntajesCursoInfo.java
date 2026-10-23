package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info;

import java.util.List;

public record HojaPuntajesCursoInfo(
        String nombre,
        List<PuntajeGeneralInfo> puntajes
) { }
