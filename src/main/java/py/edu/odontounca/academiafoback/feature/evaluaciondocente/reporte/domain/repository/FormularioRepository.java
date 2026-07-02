package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Formulario;

import java.util.Optional;

public interface FormularioRepository {
    Optional<Formulario> buscarPorId(Integer id);
}
