package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.usecase;

import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.query.BuscarFormularioPorIdQuery;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.result.FormularioResult;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.mapper.FormularioResultMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.repository.FormularioRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

@Service
public class BuscarFormularioPorIdUseCase {
    private final FormularioRepository repository;
    private final FormularioResultMapper mapper;

    public BuscarFormularioPorIdUseCase(FormularioRepository repository, FormularioResultMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public FormularioResult execute(BuscarFormularioPorIdQuery query){
        return this.repository.buscarPorId(query.id())
                .map(mapper::toResult)
                .orElseThrow(() -> new NotFoundError("Formulario", query.id()));
    }
}
