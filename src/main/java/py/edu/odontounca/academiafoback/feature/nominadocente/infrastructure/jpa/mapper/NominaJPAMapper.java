package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.model.Nomina;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.model.NominaJPAModel;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class NominaJPAMapper {

    @Autowired protected PeriodoLectivoJPAMapper periodoLectivoJPAMapper;
    @Autowired protected PlanCurricularJPAMapper planCurricularJPAMapper;

    public Nomina toDomain(NominaJPAModel nominaJpa){
        return Nomina.reconstruir(
                nominaJpa.getId(),
                nominaJpa.getNombre(),
                this.periodoLectivoJPAMapper.toDomain(nominaJpa.getPeriodo()),
                this.planCurricularJPAMapper.toDomain(nominaJpa.getPlanCurricularJPAModel()),
                // TODO: mapear los detalles cuando exista NominaDetalleJPAModel
                List.of()
        );
    }
}
