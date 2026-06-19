package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.usecase;

import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.query.BuscarPersonaPorIdQuery;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.result.PersonaResult;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.mapper.PersonaResultMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.repository.PersonaRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

@Service
public class BuscarPersonaPorIdUseCase {
    private final PersonaRepository repository;
    private final PersonaResultMapper mapper;

    public BuscarPersonaPorIdUseCase(PersonaRepository repository, PersonaResultMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PersonaResult execute(BuscarPersonaPorIdQuery query){
        return this.repository.buscarPorId(query.id())
                .map(this.mapper::toResult)
                .orElseThrow(() -> new NotFoundError("Persona", query.id()));
    }
}
