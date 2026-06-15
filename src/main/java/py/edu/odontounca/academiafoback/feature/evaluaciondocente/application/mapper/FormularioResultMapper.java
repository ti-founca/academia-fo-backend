package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.result.FormularioResult;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Formulario;

@Mapper(componentModel = "spring")
public interface FormularioResultMapper {
    FormularioResult toResult(Formulario formulario);
}
