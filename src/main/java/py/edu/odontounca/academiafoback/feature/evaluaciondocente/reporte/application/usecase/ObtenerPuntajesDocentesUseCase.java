package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract.EvaluacionQueryRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract.NominaQueryRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.*;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Estamento;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository.EstamentoRepository;
import py.edu.odontounca.academiafoback.shared.exception.NotFoundError;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ObtenerPuntajesDocentesUseCase {
    private final NominaQueryRepository nominaQueryRepository;
    private final EstamentoRepository estamentoRepository;
    private final EvaluacionQueryRepository evaluacionQueryRepository;

    public Result execute(Query query){
        if(!this.nominaQueryRepository.existeNominaPorPeriodo(query.idPeriodo()))
            throw new RuntimeException("No existen nóminas para el periodo " + query.idPeriodo());
        List<PuntajeDocenteInfo> puntajeList = this.nominaQueryRepository
                .docentesPorPeriodo(query.idPeriodo())
                .stream()
                .filter(personaInfo -> query.idDocente() == null || query.idDocente().equals(personaInfo.id()))
                .map(personaInfo -> createPuntajeFrom(personaInfo))
                .toList();
        if(query.idDocente() != null && puntajeList.isEmpty())
            throw new NotFoundError("Docente", query.idDocente());

        List<Estamento> estamentoList = this.estamentoRepository.consultarTodos();
        BigDecimal porcentajeTotal = new BigDecimal("0");
        for(Estamento e : estamentoList) porcentajeTotal = porcentajeTotal.add(e.getPorcentajePeso());
        if(!(porcentajeTotal.compareTo(new BigDecimal("100.0")) == 0))
            throw new RuntimeException("El porcentaje total de los estamentos debe sumar 100%, ahora suma " + porcentajeTotal);

        for(PuntajeDocenteInfo puntajeDocente: puntajeList){
            List<MateriaTipoDocenteInfo> materiasDocenteList = this.nominaQueryRepository.materiasPorDocentePeriodo(query.idPeriodo(), puntajeDocente.id());
            for(MateriaTipoDocenteInfo materia: materiasDocenteList){
                BigDecimal puntajePonderadoFinal = new BigDecimal("0").setScale(2, RoundingMode.HALF_UP);
                List<PuntajeDocenteInfo.PuntajeEstamento> puntajeEstamentoList = new ArrayList<>();
                for (Estamento estamento : estamentoList){
                    BigDecimal puntaje = new BigDecimal(this.evaluacionQueryRepository.obtenerPromedioPor(
                            query.idPeriodo(),
                            puntajeDocente.id(),
                            materia.materia().id(),
                            materia.tipoDocente().id(),
                            estamento.getId()
                    ).toString());
                    BigDecimal ponderacion = estamento.getPorcentajePeso().divide(new BigDecimal("100"));
                    BigDecimal puntajePonderado = puntaje.multiply(ponderacion).setScale(2, RoundingMode.HALF_UP);
                    puntajePonderadoFinal = puntajePonderadoFinal.add(puntajePonderado);

                    List<PuntajeIndicadorInfo> puntajeIndicadorInfoList = this.evaluacionQueryRepository.obtenerPuntajeIndicadorPor(
                            query.idPeriodo(),
                            estamento.getId(),
                            null,
                            query.idDocente(),
                            materia.materia().id()
                    );

                    PuntajeDocenteInfo.PuntajeEstamento puntajeEstamento = new PuntajeDocenteInfo.PuntajeEstamento(
                            this.toInfo(estamento),
                            puntaje,
                            puntajePonderado,
                            this.toPuntajeIndicadorList(puntajeIndicadorInfoList)
                    );
                    puntajeEstamentoList.add(puntajeEstamento);

                }
                puntajeDocente.puntajeMateriaList().add(
                        new PuntajeDocenteInfo.PuntajeMateria(materia.materia(), materia.tipoDocente(), puntajePonderadoFinal, puntajeEstamentoList)
                );
            }
        }
        return new Result(puntajeList);
    }

    private PuntajeDocenteInfo createPuntajeFrom(PersonaInfo personaInfo){
        return new  PuntajeDocenteInfo(personaInfo.id(), personaInfo.nombres(), personaInfo.apellidos(), personaInfo.ci(), new ArrayList<>());
    }

    private EstamentoInfo toInfo(Estamento estamento){
        return new EstamentoInfo(estamento.getId(), estamento.getDescripcion(), estamento.getPorcentajePeso());
    }

    private PuntajeDocenteInfo.PuntajeIndicador toPuntajeIndicador(PuntajeIndicadorInfo puntajeIndicadorInfo){
        return new PuntajeDocenteInfo.PuntajeIndicador(
                puntajeIndicadorInfo.indicador(),
                puntajeIndicadorInfo.puntaje(),
                puntajeIndicadorInfo.cantidad()
        );
    }

    private List<PuntajeDocenteInfo.PuntajeIndicador> toPuntajeIndicadorList(List<PuntajeIndicadorInfo> puntajeIndicadorInfoList){
        return puntajeIndicadorInfoList.stream().map(this::toPuntajeIndicador).toList();
    }

    public static record Query(Integer idPeriodo, Integer idDocente){}
    public static record Result(List<PuntajeDocenteInfo> puntajes){}
}
