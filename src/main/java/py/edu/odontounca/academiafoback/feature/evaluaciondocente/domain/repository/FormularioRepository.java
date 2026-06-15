package py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.repository;

import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Formulario;

import java.util.Optional;

public interface FormularioRepository {
    Optional<Formulario> buscarPorId(Integer id);
}
