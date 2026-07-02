package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.DetalleEvaluacion;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.DetalleEvaluacionJPAModel;

@Mapper(componentModel = "spring")
public abstract class DetalleEvaluacionJPAMapper {

    @Autowired
    protected IndicadorJPAMapper indicadorJPAMapper;

    public DetalleEvaluacion toDomain(DetalleEvaluacionJPAModel detalleEvaluacionJpa){
        return DetalleEvaluacion.reconstruir(
                detalleEvaluacionJpa.getId(),
                detalleEvaluacionJpa.getPuntaje(),
                indicadorJPAMapper.toDomain(detalleEvaluacionJpa.getIndicador())
        );
    }
}
