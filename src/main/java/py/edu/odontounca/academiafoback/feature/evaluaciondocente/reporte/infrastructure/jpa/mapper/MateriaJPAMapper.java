package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.MateriaInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Materia;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.MateriaJPAModel;

@Mapper(componentModel = "spring", uses = ReporteCursoJPAMapper.class)
public abstract class MateriaJPAMapper {

    @Autowired protected ReporteCursoJPAMapper reporteCursoJPAMapper;

    public Materia toDomain(MateriaJPAModel materiaJpa){
        return Materia.reconstruir(
                materiaJpa.getId(),
                materiaJpa.getDescripcion(),
                materiaJpa.getCodigo(),
                this.reporteCursoJPAMapper.toDomain(materiaJpa.getCurso())
        );
    }

    public abstract MateriaInfo toInfo(MateriaJPAModel materiaJpa);
}
