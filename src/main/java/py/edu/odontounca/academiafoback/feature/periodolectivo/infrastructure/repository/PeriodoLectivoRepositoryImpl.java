package py.edu.odontounca.academiafoback.feature.periodolectivo.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.periodolectivo.domain.model.PeriodoLectivo;
import py.edu.odontounca.academiafoback.feature.periodolectivo.domain.repository.PeriodoLectivoRepository;
import py.edu.odontounca.academiafoback.feature.periodolectivo.infrastructure.jpa.mapper.PeriodoLectivoJPAMapper;
import py.edu.odontounca.academiafoback.feature.periodolectivo.infrastructure.jpa.model.PeriodoLectivoJPAModel;
import py.edu.odontounca.academiafoback.feature.periodolectivo.infrastructure.jpa.repository.PeriodoLectivoJPARepository;
import py.edu.odontounca.academiafoback.shared.filter.Filter;
import py.edu.odontounca.academiafoback.shared.pagination.Page;
import py.edu.odontounca.academiafoback.shared.pagination.PageRequest;
import py.edu.odontounca.academiafoback.shared.spring.GenericSpecificationBuilder;
import py.edu.odontounca.academiafoback.shared.spring.PaginationConverter;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PeriodoLectivoRepositoryImpl implements PeriodoLectivoRepository {
    private final PeriodoLectivoJPARepository jpaRepository;
    private final PeriodoLectivoJPAMapper mapper;
    Map<String, String> fieldMapping = Map.of(
            "id", "id",
            "descripcion", "descripcion"
    );
    private final GenericSpecificationBuilder<PeriodoLectivoJPAModel> specBuilder = new GenericSpecificationBuilder<>(fieldMapping);

    @Override
    public Page<PeriodoLectivo> findByFilter(Filter filter, PageRequest pageRequest) {
        org.springframework.data.domain.Page<PeriodoLectivoJPAModel> springPage = jpaRepository.findAll(
                specBuilder.build(filter),
                PaginationConverter.toSpringPageable(pageRequest)
        );
        return PaginationConverter.fromSpringPage(springPage.map(this.mapper::toDomain));
    }

    @Override
    public Optional<PeriodoLectivo> findById(Integer id) {
        return this.jpaRepository.findById(id).map(this.mapper::toDomain);
    }
}
