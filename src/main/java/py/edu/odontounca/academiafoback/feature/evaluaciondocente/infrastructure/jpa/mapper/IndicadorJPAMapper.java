package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Indicador;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.model.IndicadorJPAModel;

@Mapper(componentModel = "spring")
public interface IndicadorJPAMapper {
    default Indicador toDomain(IndicadorJPAModel indicadorJpa){
        return Indicador.reconstruir(indicadorJpa.getId(), indicadorJpa.getNroOrden(), indicadorJpa.getDescripcion());
    };
}
