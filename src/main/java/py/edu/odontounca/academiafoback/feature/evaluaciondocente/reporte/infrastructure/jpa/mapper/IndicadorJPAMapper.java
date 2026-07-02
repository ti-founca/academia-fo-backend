package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Indicador;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.IndicadorJPAModel;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IndicadorJPAMapper {
    default Indicador toDomain(IndicadorJPAModel indicadorJpa){
        return Indicador.reconstruir(indicadorJpa.getId(), indicadorJpa.getNroOrden(), indicadorJpa.getDescripcion());
    };

    default List<Indicador> toDomainList(List<IndicadorJPAModel> indicadorJpaList){
        return indicadorJpaList.stream()
                .map(indicadorJpa -> Indicador.reconstruir(indicadorJpa.getId(), indicadorJpa.getNroOrden(), indicadorJpa.getDescripcion()))
                .collect(Collectors.toList());
    }
}
