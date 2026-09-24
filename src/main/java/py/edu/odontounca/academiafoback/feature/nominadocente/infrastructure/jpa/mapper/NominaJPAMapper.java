package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.model.Nomina;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.model.NominaJPAModel;

@Mapper(componentModel = "spring")
public abstract class NominaJPAMapper {

    @Autowired protected NominaPeriodoLectivoJPAMapper periodoLectivoJPAMapper;
    @Autowired protected PlanCurricularJPAMapper planCurricularJPAMapper;
    @Autowired protected NominaDetalleJPAMapper nominaDetalleJPAMapper;

    public Nomina toDomain(NominaJPAModel nominaJpa){
        return Nomina.reconstruir(
                nominaJpa.getId(),
                nominaJpa.getNombre(),
                this.periodoLectivoJPAMapper.toDomain(nominaJpa.getPeriodo()),
                this.planCurricularJPAMapper.toDomain(nominaJpa.getPlanCurricularJPAModel()),
                nominaJpa.getDetalles().stream().map(this.nominaDetalleJPAMapper::toDomain).toList()
        );
    }
}
