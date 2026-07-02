package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Materia;

import java.util.Optional;

public interface MateriaRepository {
    Optional<Materia> buscarPorId(Integer id);
}
