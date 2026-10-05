package py.edu.odontounca.academiafoback.feature.persona.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.persona.application.dto.info.CiudadInfo;
import py.edu.odontounca.academiafoback.feature.persona.application.dto.info.NacionalidadInfo;
import py.edu.odontounca.academiafoback.feature.persona.application.dto.info.PersonaInfo;
import py.edu.odontounca.academiafoback.feature.persona.application.dto.info.TipoPersonaInfo;
import py.edu.odontounca.academiafoback.feature.persona.domain.model.Ciudad;
import py.edu.odontounca.academiafoback.feature.persona.domain.model.Nacionalidad;
import py.edu.odontounca.academiafoback.feature.persona.domain.model.Persona;
import py.edu.odontounca.academiafoback.feature.persona.domain.model.TipoPersona;

@Mapper(componentModel = "spring")
public interface PersonaResultMapper {
    PersonaInfo toInfo(Persona persona);
    TipoPersonaInfo toInfo(TipoPersona tipoPersona);
    CiudadInfo toInfo(Ciudad ciudad);
    NacionalidadInfo toInfo(Nacionalidad nacionalidad);
}
