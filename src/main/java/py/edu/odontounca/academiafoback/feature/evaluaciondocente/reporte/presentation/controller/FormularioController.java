package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase.BuscarFormularioPorIdUseCase;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto.FormularioDTO;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.mapper.FormularioResponseMapper;

@RestController
@RequestMapping("/api/formularios")
@RequiredArgsConstructor
public class FormularioController {

    private final BuscarFormularioPorIdUseCase buscarFormularioPorIdUseCase;
    private final FormularioResponseMapper responseMapper;

    @GetMapping("{id}")
    public FormularioDTO buscarPorId(@PathVariable("id") Integer id){
        BuscarFormularioPorIdUseCase.Result result = this.buscarFormularioPorIdUseCase.execute(new BuscarFormularioPorIdUseCase.Query(id));
        return responseMapper.toResponse(result.formulario());
    }

}
