package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PersonaInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper.ReportePersonaResultMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository.ReportePersonaRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

@Service
@RequiredArgsConstructor
public class BuscarReportePersonaPorIdUseCase {
    private final ReportePersonaRepository repository;
    private final ReportePersonaResultMapper mapper;

    public BuscarReportePersonaPorIdUseCase.Result execute(BuscarReportePersonaPorIdUseCase.Query query){
        return this.repository.buscarPorId(query.id())
                .map(this.mapper::toResult)
                .orElseThrow(() -> new NotFoundError("Persona", query.id()));
    }

    public static record Query(Integer id){}
    public static record Result(PersonaInfo persona){}
}
