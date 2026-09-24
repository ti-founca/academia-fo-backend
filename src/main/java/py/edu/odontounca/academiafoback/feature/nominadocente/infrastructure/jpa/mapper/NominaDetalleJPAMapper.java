package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.model.NominaDetalle;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.model.NominaDetalleJPAModel;

@Mapper(componentModel = "spring")
public abstract class NominaDetalleJPAMapper {

    @Autowired protected DocenteJPAMapper docenteJPAMapper;
    @Autowired protected NominaMateriaJPAMapper materiaJPAMapper;
    @Autowired protected NominaTipoDocenteJPAMapper tipoDocenteJPAMapper;

    public NominaDetalle toDomain(NominaDetalleJPAModel nominaDetalleJpa){
        return NominaDetalle.reconstruir(
                this.docenteJPAMapper.toDomain(nominaDetalleJpa.getDocente()),
                this.materiaJPAMapper.toDomain(nominaDetalleJpa.getMateria()),
                this.tipoDocenteJPAMapper.toDomain(nominaDetalleJpa.getTipoDocente())
        );
    }
}
