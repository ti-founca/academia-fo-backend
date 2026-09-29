package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto;

public record PuntajeMateriaDTO(
        MateriaDTO materia,
        TipoDocenteDTO tipoDocente,
        double promedioGeneral
) { }
