package py.edu.odontounca.academiafoback.feature.nominadocente.presentation.dto;

public record NominaDetalleDTO(
        DocenteDTO docente,
        MateriaDTO materia,
        TipoDocenteDTO tipoDocente
) {
}
