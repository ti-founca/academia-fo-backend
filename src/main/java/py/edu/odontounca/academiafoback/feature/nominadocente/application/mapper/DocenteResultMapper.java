package py.edu.odontounca.academiafoback.feature.nominadocente.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.application.dto.info.DocenteInfo;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.model.Docente;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocenteResultMapper {
    DocenteInfo toInfo(Docente docente);
    List<DocenteInfo> toInfoList(List<Docente> docentes);
}
