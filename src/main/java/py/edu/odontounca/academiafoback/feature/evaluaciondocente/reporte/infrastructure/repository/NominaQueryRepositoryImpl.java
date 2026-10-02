package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract.NominaQueryRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.MateriaTipoDocenteInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PersonaInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper.MateriaJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper.PersonaJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper.TipoDocenteJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.repository.NominaQueryJPARepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NominaQueryRepositoryImpl implements NominaQueryRepository {
    private final NominaQueryJPARepository  nominaQueryJPARepository;
    private final PersonaJPAMapper personaJPAMapper;
    private final MateriaJPAMapper materiaJPAMapper;
    private final TipoDocenteJPAMapper tipoDocenteJPAMapper;

    @Override
    public List<PersonaInfo> docentesPorPeriodo(Integer idPeriodo) {
        return this.nominaQueryJPARepository
                .docentesPorPeriodo(idPeriodo)
                .stream()
                .map(this.personaJPAMapper::toInfo)
                .toList();
    }

    @Override
    public boolean existeNominaPorPeriodo(Integer idPeriodo) {
        return this.nominaQueryJPARepository.existsByIdPeriodo(idPeriodo);
    }

    @Override
    public List<MateriaTipoDocenteInfo> materiasPorDocentePeriodo(Integer idPeriodo, Integer idDocente) {
        // Un docente puede repetir materia y tipo en distintas nóminas del mismo periodo
        return this.nominaQueryJPARepository
                .materiasPorDocentePeriodo(idPeriodo, idDocente)
                .stream()
                .map(det -> new MateriaTipoDocenteInfo(
                        this.materiaJPAMapper.toInfo(det.getMateria()),
                        this.tipoDocenteJPAMapper.toInfo(det.getTipoDocente())
                ))
                .distinct()
                .toList();
    }
}
