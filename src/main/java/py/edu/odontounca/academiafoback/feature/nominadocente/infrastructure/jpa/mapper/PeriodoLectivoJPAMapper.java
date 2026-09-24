package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.model.PeriodoLectivo;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.model.PeriodoLectivoJPAModel;

@Mapper(componentModel = "spring")
public interface PeriodoLectivoJPAMapper {
    default PeriodoLectivo toDomain(PeriodoLectivoJPAModel periodoLectivoJpa){
        return PeriodoLectivo.reconstruir(
                periodoLectivoJpa.getId(),
                periodoLectivoJpa.getNombre()
        );
    };
}
