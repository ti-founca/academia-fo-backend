package py.edu.odontounca.academiafoback.feature.autenticacion.application.dto.info;

public record UsuarioAutenticadoInfo(
        Integer id,
        String nombre,
        String apellido,
        String correo
) {
}
