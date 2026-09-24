package py.edu.odontounca.academiafoback.feature.nominadocente.application.dto.info;

public record NominaDetalleInfo(
        DocenteInfo docente,
        MateriaInfo materia,
        TipoDocenteInfo tipoDocente
) {
}
