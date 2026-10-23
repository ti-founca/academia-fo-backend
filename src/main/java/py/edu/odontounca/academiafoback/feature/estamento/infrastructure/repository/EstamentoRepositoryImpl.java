package py.edu.odontounca.academiafoback.feature.estamento.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.estamento.domain.model.Estamento;
import py.edu.odontounca.academiafoback.feature.estamento.domain.repository.EstamentoRepository;
import py.edu.odontounca.academiafoback.feature.estamento.infrastructure.jpa.mapper.EstamentoJPAMapper;
import py.edu.odontounca.academiafoback.feature.estamento.infrastructure.jpa.model.EstamentoJPAModel;
import py.edu.odontounca.academiafoback.feature.estamento.infrastructure.jpa.repository.EstamentoJPARepository;
import py.edu.odontounca.academiafoback.shared.filter.Filter;
import py.edu.odontounca.academiafoback.shared.pagination.Page;
import py.edu.odontounca.academiafoback.shared.pagination.PageRequest;
import py.edu.odontounca.academiafoback.shared.spring.GenericSpecificationBuilder;
import py.edu.odontounca.academiafoback.shared.spring.PaginationConverter;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class EstamentoRepositoryImpl implements EstamentoRepository {
    private final EstamentoJPARepository jpaRepository;
    private final EstamentoJPAMapper mapper;
    Map<String, String> fieldMapping = Map.of(
            "id", "id",
            "descripcion", "descripcion"
    );
    private final GenericSpecificationBuilder<EstamentoJPAModel> specBuilder = new GenericSpecificationBuilder<>(fieldMapping);

    @Override
    public Page<Estamento> findByFilter(Filter filter, PageRequest pageRequest) {
        org.springframework.data.domain.Page<EstamentoJPAModel> springPage = jpaRepository.findAll(
                specBuilder.build(filter),
                PaginationConverter.toSpringPageable(pageRequest)
        );
        return PaginationConverter.fromSpringPage(springPage.map(this.mapper::toDomain));
    }
}
