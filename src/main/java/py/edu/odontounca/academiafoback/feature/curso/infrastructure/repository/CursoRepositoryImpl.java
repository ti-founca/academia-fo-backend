package py.edu.odontounca.academiafoback.feature.curso.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.curso.domain.model.Curso;
import py.edu.odontounca.academiafoback.feature.curso.domain.repository.CursoRepository;
import py.edu.odontounca.academiafoback.feature.curso.infrastructure.jpa.mapper.CursoJPAMapper;
import py.edu.odontounca.academiafoback.feature.curso.infrastructure.jpa.model.CursoJPAModel;
import py.edu.odontounca.academiafoback.feature.curso.infrastructure.jpa.repository.CursoJPARepository;
import py.edu.odontounca.academiafoback.shared.filter.Filter;
import py.edu.odontounca.academiafoback.shared.pagination.Page;
import py.edu.odontounca.academiafoback.shared.pagination.PageRequest;
import py.edu.odontounca.academiafoback.shared.spring.GenericSpecificationBuilder;
import py.edu.odontounca.academiafoback.shared.spring.PaginationConverter;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CursoRepositoryImpl implements CursoRepository {
    private final CursoJPARepository jpaRepository;
    private final CursoJPAMapper jpaMapper;
    private final Map<String, String> fieldMapping = Map.of(
            "id", "id",
            "descripcion", "descripcion"
    );
    private final GenericSpecificationBuilder<CursoJPAModel> specBuilder = new GenericSpecificationBuilder<>(fieldMapping);

    @Override
    public Page<Curso> consultarTodos(Filter filter, PageRequest pageRequest) {
        org.springframework.data.domain.Page<CursoJPAModel> springPage = this.jpaRepository.findAll(
                this.specBuilder.build(filter),
                PaginationConverter.toSpringPageable(pageRequest)
        );
        return PaginationConverter.fromSpringPage(springPage.map(this.jpaMapper::toDomain));
    }
}
