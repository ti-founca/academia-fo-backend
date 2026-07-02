package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Persona;

import java.util.Optional;

public interface PersonaRepository {
    Optional<Persona> buscarPorId(Integer id);
}
