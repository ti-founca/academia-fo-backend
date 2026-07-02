package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper;

import org.mapstruct.Mapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.FormularioInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.BuscarFormularioPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Formulario;

@Mapper(componentModel = "spring")
public interface FormularioResultMapper {
    BuscarFormularioPorIdUseCase.Result toResult(Formulario formulario);
    FormularioInfo toInfo(Formulario formulario);
}
