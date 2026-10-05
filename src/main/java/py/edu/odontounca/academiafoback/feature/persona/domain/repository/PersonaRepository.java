package py.edu.odontounca.academiafoback.feature.persona.domain.repository;

import py.edu.odontounca.academiafoback.feature.persona.domain.model.Persona;

import java.util.Optional;

public interface PersonaRepository {
    Optional<Persona> buscarPorId(Integer id);
}
