package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.usecase;

import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.query.BuscarIndicadorPorIdQuery;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.result.IndicadorResult;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.mapper.IndicadorResultMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.repository.IndicadorRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

@Service
public class BuscarIndicadorPorIdUseCase {

    private final IndicadorRepository repository;
    private final IndicadorResultMapper mapper;

    public BuscarIndicadorPorIdUseCase(IndicadorRepository repository, IndicadorResultMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public IndicadorResult execute(BuscarIndicadorPorIdQuery query){
        return this.repository.buscarPorId(query.id())
                .map(mapper::toResult)
                .orElseThrow(()-> new NotFoundError("Indicador", query.id()));
    }
}
