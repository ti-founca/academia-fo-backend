package py.edu.odontounca.academiafoback.feature.persona.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.persona.application.dto.info.PersonaInfo;
import py.edu.odontounca.academiafoback.feature.persona.application.mapper.PersonaResultMapper;
import py.edu.odontounca.academiafoback.feature.persona.domain.repository.PersonaRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

@Service
@RequiredArgsConstructor
public class BuscarPersonaPorIdUseCase {
    private final PersonaRepository repository;
    private final PersonaResultMapper mapper;

    public Result execute(Query query){
        return this.repository.buscarPorId(query.id())
                .map(persona -> new Result(this.mapper.toInfo(persona)))
                .orElseThrow(() -> new NotFoundError("Persona", query.id()));
    }

    public static record Query(Integer id){ }
    public static record Result(PersonaInfo persona){ }
}
