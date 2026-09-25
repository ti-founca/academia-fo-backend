package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.model.Docente;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.model.DocenteJPAModel;

@Mapper(componentModel = "spring")
public interface DocenteJPAMapper {
    default Docente toDomain(DocenteJPAModel docenteJpa){
        return Docente.reconstruir(
                docenteJpa.getId(),
                docenteJpa.getNombres(),
                docenteJpa.getApellidos(),
                docenteJpa.getCi()
        );
    };
}
