package py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.repository;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Indicador;

import java.util.Optional;

public interface IndicadorRepository {
    Optional<Indicador> buscarPorId(Integer id);
}
