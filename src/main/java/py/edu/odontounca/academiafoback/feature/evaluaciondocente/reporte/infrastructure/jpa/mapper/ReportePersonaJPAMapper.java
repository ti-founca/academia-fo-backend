package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PersonaInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Persona;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.PersonaJPAModel;

@Mapper(componentModel = "spring")
public interface ReportePersonaJPAMapper {
    default Persona toDomain(PersonaJPAModel personaJpa){
        return Persona.reconstruir(personaJpa.getId(), personaJpa.getNombres(), personaJpa.getApellidos(), personaJpa.getCi());
    };

    PersonaInfo toInfo(PersonaJPAModel personaJpa);
}
