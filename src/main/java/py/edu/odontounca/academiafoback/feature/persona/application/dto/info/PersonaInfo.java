package py.edu.odontounca.academiafoback.feature.persona.application.dto.info;

import java.time.LocalDate;

public record PersonaInfo(
        Integer id,
        String nombres,
        String apellidos,
        String ci,
        String email,
        String sexo,
        String estadoCivil,
        LocalDate fechaNacimiento,
        TipoPersonaInfo tipoPersona,
        CiudadInfo ciudad,
        NacionalidadInfo nacionalidad
) {
}
