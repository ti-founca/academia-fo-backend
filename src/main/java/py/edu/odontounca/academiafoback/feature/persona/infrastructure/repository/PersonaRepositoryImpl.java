package py.edu.odontounca.academiafoback.feature.persona.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.persona.domain.model.Persona;
import py.edu.odontounca.academiafoback.feature.persona.domain.repository.PersonaRepository;
import py.edu.odontounca.academiafoback.feature.persona.infrastructure.jpa.mapper.PersonaJPAMapper;
import py.edu.odontounca.academiafoback.feature.persona.infrastructure.jpa.repository.PersonaJPARepository;

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
