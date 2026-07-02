package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Formulario;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository.FormularioRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper.FormularioJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.repository.FormularioJPARepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FormularioRepositoryImpl implements FormularioRepository {

    private final FormularioJPARepository jpaRepository;
    private final FormularioJPAMapper mapper;

    @Override
    public Optional<Formulario> buscarPorId(Integer id) {
        return this.jpaRepository.findById(id).map(mapper::toDomain);
    }
}
