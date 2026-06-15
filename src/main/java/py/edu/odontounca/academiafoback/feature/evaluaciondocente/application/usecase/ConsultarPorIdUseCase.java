package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.usecase;

import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.query.ConsultarPorIdQuery;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.result.MateriaResult;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.mapper.MateriaResultMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.repository.MateriaRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

import java.util.Optional;

@Service
public class ConsultarPorIdUseCase {

    private final MateriaRepository repository;
    private final MateriaResultMapper mapper;

    public ConsultarPorIdUseCase(MateriaRepository repository, MateriaResultMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public MateriaResult execute(ConsultarPorIdQuery query){
        return this.repository.buscarPorId(
                query.id()
        ).map(mapper::toResult).orElseThrow(
                () -> new NotFoundError("Materia", query.id())
        );
    }

}
