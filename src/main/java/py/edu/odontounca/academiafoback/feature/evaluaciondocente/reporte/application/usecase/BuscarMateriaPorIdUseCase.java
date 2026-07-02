package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.MateriaInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.mapper.MateriaResultMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository.MateriaRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

@Service
@RequiredArgsConstructor
public class BuscarMateriaPorIdUseCase {
    private final MateriaRepository repository;
    private final MateriaResultMapper mapper;

    public BuscarMateriaPorIdUseCase.Result execute(BuscarMateriaPorIdUseCase.Query query){
        return this.repository.buscarPorId(query.id())
                .map(mapper::toResult)
                .orElseThrow(() -> new NotFoundError("Materia", query.id()));
    }

    public static record Query(Integer id){}
    public static record Result(MateriaInfo materia){}
}
