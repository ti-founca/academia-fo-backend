package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.info.IndicadorInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.mapper.IndicadorResultMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.repository.IndicadorRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

@Service
@RequiredArgsConstructor
public class BuscarIndicadorPorIdUseCase {
    private final IndicadorRepository repository;
    private final IndicadorResultMapper mapper;

    public BuscarIndicadorPorIdUseCase.Result execute(BuscarIndicadorPorIdUseCase.Query query){
        return this.repository.buscarPorId(query.id())
                .map(mapper::toResult)
                .orElseThrow(()-> new NotFoundError("Indicador", query.id()));
    }

    public static record Query(Integer id){}
    public static record Result(IndicadorInfo indicador){}
}
