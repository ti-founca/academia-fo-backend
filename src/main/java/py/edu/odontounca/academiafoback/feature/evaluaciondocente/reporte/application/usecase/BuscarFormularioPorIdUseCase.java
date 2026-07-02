package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.FormularioInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper.FormularioResultMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository.FormularioRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

@Service
@RequiredArgsConstructor
public class BuscarFormularioPorIdUseCase {
    private final FormularioRepository repository;
    private final FormularioResultMapper mapper;

    public BuscarFormularioPorIdUseCase.Result execute(BuscarFormularioPorIdUseCase.Query query){
        return this.repository.buscarPorId(query.id())
                .map(mapper::toResult)
                .orElseThrow(() -> new NotFoundError("Formulario", query.id()));
    }

    public static record Query(Integer id){}
    public static record Result(FormularioInfo formulario){}
}
