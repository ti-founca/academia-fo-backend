package py.edu.odontounca.academiafoback.feature.persona.presentation.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.persona.application.dto.info.CiudadInfo;
import py.edu.odontounca.academiafoback.feature.persona.application.dto.info.NacionalidadInfo;
import py.edu.odontounca.academiafoback.feature.persona.application.dto.info.PersonaInfo;
import py.edu.odontounca.academiafoback.feature.persona.application.dto.info.TipoPersonaInfo;
import py.edu.odontounca.academiafoback.feature.persona.presentation.dto.CiudadDTO;
import py.edu.odontounca.academiafoback.feature.persona.presentation.dto.NacionalidadDTO;
import py.edu.odontounca.academiafoback.feature.persona.presentation.dto.PersonaDTO;
import py.edu.odontounca.academiafoback.feature.persona.presentation.dto.TipoPersonaDTO;

@Mapper(componentModel = "spring")
public interface PersonaResponseMapper {
    PersonaDTO toResponse(PersonaInfo personaInfo);
    TipoPersonaDTO toResponse(TipoPersonaInfo tipoPersonaInfo);
    CiudadDTO toResponse(CiudadInfo ciudadInfo);
    NacionalidadDTO toResponse(NacionalidadInfo nacionalidadInfo);
}
