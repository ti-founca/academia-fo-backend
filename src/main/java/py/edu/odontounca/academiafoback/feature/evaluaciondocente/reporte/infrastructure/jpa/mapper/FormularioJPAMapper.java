package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Formulario;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.FormularioJPAModel;

@Mapper(componentModel = "spring")
public abstract class FormularioJPAMapper {

    @Autowired
    protected IndicadorJPAMapper indicadorJPAMapper;

    public Formulario toDomain(FormularioJPAModel formularioJpa){
        return Formulario.reconstruir(
                formularioJpa.getId(),
                formularioJpa.getDescripcion(),
                formularioJpa.getFechaApertura(),
                formularioJpa.getFechaCierre(),
                formularioJpa.isActivo(),
                formularioJpa.getIndicadores().stream().map(indicadorJPAMapper::toDomain).toList()
        );
    };
}
