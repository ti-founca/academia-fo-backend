package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Estamento;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.model.EstamentoJPAModel;

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
