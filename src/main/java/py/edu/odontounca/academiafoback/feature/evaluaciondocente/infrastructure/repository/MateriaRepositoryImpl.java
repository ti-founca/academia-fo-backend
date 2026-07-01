package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Materia;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.repository.MateriaRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.mapper.MateriaJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.repository.MateriaJPARepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MateriaRepositoryImpl implements MateriaRepository {

    private final MateriaJPARepository jpaRepository;
    private final MateriaJPAMapper mapper;

    @Override
    public Optional<Materia> buscarPorId(Integer id) {
        return this.jpaRepository.findById(id).map(this.mapper::toDomain);
    }
}
