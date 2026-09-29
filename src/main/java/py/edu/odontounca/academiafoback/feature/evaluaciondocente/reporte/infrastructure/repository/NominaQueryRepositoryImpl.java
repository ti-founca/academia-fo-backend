package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract.NominaQueryRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.MateriaTipoDocenteInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.PersonaInfo;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper.MateriaJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper.PersonaJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.mapper.TipoDocenteJPAMapper;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.NominaJPAModel;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.PersonaJPAModel;
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
                .obtenerDocentesPorPeriodo(idPeriodo)
                .stream()
                .map(this.personaJPAMapper::toInfo)
                .toList();
    }

    @Override
    public Integer iddNominaPorPeriodo(Integer idPeriodo) {
        List<NominaJPAModel> nominasList = this.nominaQueryJPARepository.nominasPorIdPeriodo(idPeriodo);
        if (nominasList.isEmpty()) return -1;
        return nominasList.get(0).getId();
    }

    @Override
    public List<MateriaTipoDocenteInfo> materiasPorDocenteNomina(Integer idNomina, Integer idDocente) {
        return this.nominaQueryJPARepository
                .materiasPorDocenteNomina(idNomina, idDocente)
                .stream()
                .map(det -> new MateriaTipoDocenteInfo(
                        this.materiaJPAMapper.toInfo(det.getMateria()),
                        this.tipoDocenteJPAMapper.toInfo(det.getTipoDocente())
                ))
                .toList();
    }
}
