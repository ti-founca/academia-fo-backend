package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Materia;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.model.MateriaJPAModel;

@Mapper(componentModel = "spring")
public interface MateriaJPAMapper {
    default Materia toDomain(MateriaJPAModel materiaJpa){
        return Materia.reconstruir(materiaJpa.getId(), materiaJpa.getDescripcion(), materiaJpa.getCodigo());
    }
}
