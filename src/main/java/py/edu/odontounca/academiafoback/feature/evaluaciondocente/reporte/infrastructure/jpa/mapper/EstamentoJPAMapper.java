package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Estamento;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.EstamentoJPAModel;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface EstamentoJPAMapper {
    default Estamento toDomain(EstamentoJPAModel estamentoJpa){
        return Estamento.reconstruir(
                estamentoJpa.getId(),
                estamentoJpa.getDescripcion(),
                new BigDecimal(estamentoJpa.getPorcentajePeso())
        );
    }
}
