package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Indicador;

import java.util.Optional;

public interface IndicadorRepository {
    Optional<Indicador> buscarPorId(Integer id);
}
