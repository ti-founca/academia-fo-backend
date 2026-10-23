package py.edu.odontounca.academiafoback.feature.autenticacion.presentation.dto;

public record UsuarioAutenticadoDTO(
        Integer id,
        String nombre,
        String apellido,
        String correo
) {
}
