package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.model.MateriaJPAModel;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.model.Materia;

@Mapper(componentModel = "spring")
public interface NominaMateriaJPAMapper {
    default Materia toDomain(MateriaJPAModel materiaJpa){
        return Materia.reconstruir(
                materiaJpa.getId(),
                materiaJpa.getDescripcion(),
                materiaJpa.getCodigo()
        );
    };
}
