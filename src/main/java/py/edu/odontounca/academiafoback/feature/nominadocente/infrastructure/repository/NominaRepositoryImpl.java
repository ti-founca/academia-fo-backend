package py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.model.Docente;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.model.Nomina;
import py.edu.odontounca.academiafoback.feature.nominadocente.domain.repository.NominaRepository;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.mapper.DocenteJPAMapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.mapper.NominaJPAMapper;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.model.NominaJPAModel;
import py.edu.odontounca.academiafoback.feature.nominadocente.infrastructure.jpa.repository.NominaJPARepository;
import py.edu.odontounca.academiafoback.shared.filter.Filter;
import py.edu.odontounca.academiafoback.shared.pagination.Page;
import py.edu.odontounca.academiafoback.shared.pagination.PageRequest;
import py.edu.odontounca.academiafoback.shared.spring.GenericSpecificationBuilder;
import py.edu.odontounca.academiafoback.shared.spring.PaginationConverter;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NominaRepositoryImpl implements NominaRepository {
    private final NominaJPARepository jpaRepository;
    private final NominaJPAMapper mapper;
    private final DocenteJPAMapper docenteMapper;
    private final Map<String, String> fieldMapping = Map.of(
            "id", "id",
            "nombre", "nombre",
            "periodoLectivoId", "periodo.id",
            "planCurricularId", "planCurricularJPAModel.id"
    );
    private final GenericSpecificationBuilder<NominaJPAModel> specBuilder = new GenericSpecificationBuilder<>(fieldMapping);

    @Override
    public Page<Nomina> findByFilter(Filter filter, PageRequest pageRequest) {
        org.springframework.data.domain.Page<NominaJPAModel> springPage = this.jpaRepository.findAll(
                this.specBuilder.build(filter),
                PaginationConverter.toSpringPageable(pageRequest)
        );
        return PaginationConverter.fromSpringPage(springPage.map(this.mapper::toDomain));
    }

    @Override
    public Optional<Nomina> findById(Integer id) {
        return this.jpaRepository.findById(id).map(this.mapper::toDomain);
    }

    @Override
    public boolean existsById(Integer id) {
        return this.jpaRepository.existsById(id);
    }

    @Override
    public List<Docente> findDocentesByNominaId(Integer nominaId) {
        return this.jpaRepository.findDocentesByNominaId(nominaId).stream()
                .map(this.docenteMapper::toDomain)
                .toList();
    }
}
