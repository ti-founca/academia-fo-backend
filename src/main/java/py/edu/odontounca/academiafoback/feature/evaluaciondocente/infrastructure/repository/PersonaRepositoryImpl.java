package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Persona;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.repository.PersonaRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.mapper.PersonaJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.repository.PersonaJPARepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonaRepositoryImpl implements PersonaRepository {
    private final PersonaJPARepository jpaRepository;
    private final PersonaJPAMapper mapper;

    @Override
    public Optional<Persona> buscarPorId(Integer id) {
        return this.jpaRepository.findById(id).map(this.mapper::toDomain);
    }
}
