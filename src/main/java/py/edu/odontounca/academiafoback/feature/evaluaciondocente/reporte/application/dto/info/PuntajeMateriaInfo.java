package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info;

import java.math.BigDecimal;

public record PuntajeMateriaInfo(
        MateriaInfo materia,
        TipoDocenteInfo tipoDocente,
        BigDecimal promedioGeneral
) { }
