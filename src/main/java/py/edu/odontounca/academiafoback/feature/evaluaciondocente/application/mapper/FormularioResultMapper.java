package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.info.FormularioInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.usecase.BuscarFormularioPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Formulario;

@Mapper(componentModel = "spring")
public interface FormularioResultMapper {
    BuscarFormularioPorIdUseCase.Result toResult(Formulario formulario);
    FormularioInfo toInfo(Formulario formulario);
}
