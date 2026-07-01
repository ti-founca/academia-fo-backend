package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.TipoDocente;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.repository.TipoDocenteRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.mapper.TipoDocenteJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.repository.TipoDocenteJPARepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TipoDocenteRepositoryImpl implements TipoDocenteRepository {
    private final TipoDocenteJPARepository jpaRepository;
    private final TipoDocenteJPAMapper mapper;

    @Override
    public List<TipoDocente> consultarTodos() {
        return this.jpaRepository
                .findAll()
                .stream()
                .map(this.mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<TipoDocente> buscarPorId(Integer id) {
        return this.jpaRepository.findById(id).map(this.mapper::toDomain);
    }
}
