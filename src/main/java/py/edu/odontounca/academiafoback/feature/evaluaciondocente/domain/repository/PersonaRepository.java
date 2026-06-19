package py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.repository;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Persona;

import java.util.Optional;

public interface PersonaRepository {
    Optional<Persona> buscarPorId(Integer id);
}
