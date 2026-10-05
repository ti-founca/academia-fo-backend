package py.edu.odontounca.academiafoback.feature.persona.presentation.dto;

import java.time.LocalDate;

public record PersonaDTO(
        Integer id,
        String nombres,
        String apellidos,
        String ci,
        String email,
        String sexo,
        String estadoCivil,
        LocalDate fechaNacimiento,
        TipoPersonaDTO tipoPersona,
        CiudadDTO ciudad,
        NacionalidadDTO nacionalidad
) {
}
