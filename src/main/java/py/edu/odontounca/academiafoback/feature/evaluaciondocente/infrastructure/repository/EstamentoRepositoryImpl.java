package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model.Estamento;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.repository.EstamentoRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.mapper.EstamentoJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.repository.EstamentoJPARepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EstamentoRepositoryImpl implements EstamentoRepository {
    private final EstamentoJPARepository jpaRepository;
    private final EstamentoJPAMapper mapper;

    @Override
    public List<Estamento> consultarTodos() {
        return this.jpaRepository.findAll()
                .stream()
                .map(this.mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Estamento> buscarPorId(Integer id) {
        return this.jpaRepository.findById(id).map(this.mapper::toDomain);
    }
}
