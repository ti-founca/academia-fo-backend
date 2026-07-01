package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Indicador;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.repository.IndicadorRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.mapper.IndicadorJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.repository.IndicadorJPARepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class IndicadorRepositoryImpl implements IndicadorRepository {

    private final IndicadorJPARepository repository;
    private final IndicadorJPAMapper mapper;

    @Override
    public Optional<Indicador> buscarPorId(Integer id) {
        return repository.findById(id).map(mapper::toDomain);
    }
}
