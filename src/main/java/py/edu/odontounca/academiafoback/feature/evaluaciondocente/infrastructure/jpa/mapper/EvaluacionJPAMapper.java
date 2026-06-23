package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Evaluacion;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.model.EvaluacionJPAModel;

@Mapper(componentModel = "spring")
public abstract class EvaluacionJPAMapper {

    @Autowired protected FormularioJPAMapper formularioJPAMapper;
    @Autowired protected MateriaJPAMapper materiaJPAMapper;
    @Autowired protected PersonaJPAMapper personaJPAMapper;
    @Autowired protected DetalleEvaluacionJPAMapper detalleEvaluacionJPAMapper;

    public Evaluacion toDomain(EvaluacionJPAModel evaluacionJpa){
        return Evaluacion.reconstruir(
                evaluacionJpa.getId(),
                evaluacionJpa.getFechaInicio(),
                evaluacionJpa.getFechaFin(),
                evaluacionJpa.getTerminado(),
                this.formularioJPAMapper.toDomain(evaluacionJpa.getFormulario()),
                this.materiaJPAMapper.toDomain(evaluacionJpa.getMateria()),
                this.personaJPAMapper.toDomain(evaluacionJpa.getEvaluado()),
                this.personaJPAMapper.toDomain(evaluacionJpa.getEvaluador()),
                evaluacionJpa.getDetalles().stream().map(this.detalleEvaluacionJPAMapper::toDomain).toList()
        );
    }
}
