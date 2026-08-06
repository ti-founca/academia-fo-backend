package py.edu.odontounca.academiafoback.feature.periodolectivo.infrastructure.jpa.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.periodolectivo.domain.model.PeriodoLectivo;
import py.edu.odontounca.academiafoback.feature.periodolectivo.domain.model.vo.NumeroSemestre;
import py.edu.odontounca.academiafoback.feature.periodolectivo.domain.model.vo.TipoPeriodoLectivo;
import py.edu.odontounca.academiafoback.feature.periodolectivo.infrastructure.jpa.model.PeriodoLectivoJPAModel;

@Mapper(componentModel = "spring")
public interface PeriodoLectivoJPAMapper {
    default PeriodoLectivo toDomain(PeriodoLectivoJPAModel periodoLectivoJpa){
        return PeriodoLectivo.reconstruir(
                periodoLectivoJpa.getId(),
                periodoLectivoJpa.getDescripcion(),
                periodoLectivoJpa.getAnho(),
                periodoLectivoJpa.isCerrado(),
                TipoPeriodoLectivo.fromCode(periodoLectivoJpa.getTipo()),
                NumeroSemestre.fromCode(periodoLectivoJpa.getSemestre()),
                periodoLectivoJpa.getFechaInicio(),
                periodoLectivoJpa.getFechaFin()
        );
    };
}
