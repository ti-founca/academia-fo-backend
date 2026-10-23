package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Estamento;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository.EstamentoRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper.ReporteEstamentoJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.repository.ReporteEstamentoJPARepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReporteEstamentoRepositoryImpl implements EstamentoRepository {
    private final ReporteEstamentoJPARepository jpaRepository;
    private final ReporteEstamentoJPAMapper mapper;

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
