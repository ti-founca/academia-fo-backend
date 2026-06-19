package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Persona;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.model.PersonaJPAModel;

@Mapper(componentModel = "spring")
public interface PersonaJPAMapper {
    default Persona toDomain(PersonaJPAModel personaJpa){
        return Persona.reconstruir(personaJpa.getId(), personaJpa.getNombres(), personaJpa.getApellidos(), personaJpa.getCi());
    };
}
