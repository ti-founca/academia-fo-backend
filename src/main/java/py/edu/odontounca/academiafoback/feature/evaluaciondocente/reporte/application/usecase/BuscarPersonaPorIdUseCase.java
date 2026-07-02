package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PersonaInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper.PersonaResultMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository.PersonaRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

@Service
@RequiredArgsConstructor
public class BuscarPersonaPorIdUseCase {
    private final PersonaRepository repository;
    private final PersonaResultMapper mapper;

    public BuscarPersonaPorIdUseCase.Result execute(BuscarPersonaPorIdUseCase.Query query){
        return this.repository.buscarPorId(query.id())
                .map(this.mapper::toResult)
                .orElseThrow(() -> new NotFoundError("Persona", query.id()));
    }

    public static record Query(Integer id){}
    public static record Result(PersonaInfo persona){}
}
