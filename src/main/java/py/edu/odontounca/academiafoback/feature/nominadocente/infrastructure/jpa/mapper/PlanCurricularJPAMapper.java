package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.model.PlanCurricular;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.model.PlanCurricularJPAModel;

@Mapper(componentModel = "spring")
public interface PlanCurricularJPAMapper {
    default PlanCurricular toDomain(PlanCurricularJPAModel planCurricularJpa){
        return PlanCurricular.reconstruir(
                planCurricularJpa.getId(),
                planCurricularJpa.getNombre()
        );
    };
}
