package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.EstamentoInfo;
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

    @Mapping(source = "porcentajePeso", target = "porcentajePeso", qualifiedByName = "strToBigDecimal")
    EstamentoInfo toInfo(EstamentoJPAModel estamentoJpa);

    @Named("strToBigDecimal")
    default BigDecimal strToBigDecimal(String number){
        return new BigDecimal(number);
    }
}
