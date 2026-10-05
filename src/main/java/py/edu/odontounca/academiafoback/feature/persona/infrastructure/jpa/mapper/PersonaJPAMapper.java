package py.edu.odontounca.academiafoback.feature.persona.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.persona.domain.model.Ciudad;
import py.edu.odontounca.academiafoback.feature.persona.domain.model.Nacionalidad;
import py.edu.odontounca.academiafoback.feature.persona.domain.model.Persona;
import py.edu.odontounca.academiafoback.feature.persona.domain.model.TipoPersona;
import py.edu.odontounca.academiafoback.feature.persona.domain.model.vo.EstadoCivil;
import py.edu.odontounca.academiafoback.feature.persona.domain.model.vo.Sexo;
import py.edu.odontounca.academiafoback.feature.persona.infrastructure.jpa.model.CiudadJPAModel;
import py.edu.odontounca.academiafoback.feature.persona.infrastructure.jpa.model.NacionalidadJPAModel;
import py.edu.odontounca.academiafoback.feature.persona.infrastructure.jpa.model.PersonaJPAModel;
import py.edu.odontounca.academiafoback.feature.persona.infrastructure.jpa.model.TipoPersonaJPAModel;

@Mapper(componentModel = "spring")
public interface PersonaJPAMapper {
    default Persona toDomain(PersonaJPAModel personaJpa){
        return Persona.reconstruir(
                personaJpa.getId(),
                personaJpa.getNombres(),
                personaJpa.getApellidos(),
                personaJpa.getCi(),
                personaJpa.getEmail(),
                Sexo.fromCode(personaJpa.getSexo()),
                personaJpa.getEstadoCivil() != null ? EstadoCivil.fromCode(personaJpa.getEstadoCivil()) : null,
                personaJpa.getFechaNacimiento(),
                toDomain(personaJpa.getTipoPersona()),
                toDomain(personaJpa.getCiudad()),
                toDomain(personaJpa.getNacionalidad())
        );
    };

    default TipoPersona toDomain(TipoPersonaJPAModel tipoPersonaJpa){
        if(tipoPersonaJpa == null) return null;
        return TipoPersona.reconstruir(tipoPersonaJpa.getId(), tipoPersonaJpa.getDescripcion());
    };

    default Ciudad toDomain(CiudadJPAModel ciudadJpa){
        if(ciudadJpa == null) return null;
        return Ciudad.reconstruir(ciudadJpa.getId(), ciudadJpa.getDescripcion());
    };

    default Nacionalidad toDomain(NacionalidadJPAModel nacionalidadJpa){
        if(nacionalidadJpa == null) return null;
        return Nacionalidad.reconstruir(nacionalidadJpa.getId(), nacionalidadJpa.getDescripcion());
    };
}
