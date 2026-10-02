package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract.EvaluacionQueryRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.contract.NominaQueryRepository;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.application.dto.info.*;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model.Estamento;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.repository.EstamentoRepository;

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
                .map(personaInfo -> createPuntajeFrom(personaInfo))
                .toList();

        List<Estamento> estamentoList = this.estamentoRepository.consultarTodos();
        BigDecimal porcentajeTotal = new BigDecimal("0");
        for(Estamento e : estamentoList) porcentajeTotal = porcentajeTotal.add(e.getPorcentajePeso());
        if(!(porcentajeTotal.compareTo(new BigDecimal("100.0")) == 0))
            throw new RuntimeException("El porcentaje total de los estamentos debe sumar 100%, ahora suma " + porcentajeTotal);

        for(PuntajeDocenteInfo puntajeDocente: puntajeList){
            List<MateriaTipoDocenteInfo> materiasDocenteList = this.nominaQueryRepository.materiasPorDocentePeriodo(query.idPeriodo(), puntajeDocente.id());
            for(MateriaTipoDocenteInfo materia: materiasDocenteList){
                BigDecimal puntajePonderadoFinal = new BigDecimal("0").setScale(2, RoundingMode.HALF_UP);
                for (Estamento estamento : estamentoList){
                    Double promedio = this.evaluacionQueryRepository.obtenerPromedioPor(
                            query.idPeriodo(),
                            puntajeDocente.id(),
                            materia.materia().id(),
                            materia.tipoDocente().id(),
                            estamento.getId()
                    );
                    BigDecimal ponderacion = estamento.getPorcentajePeso().divide(new BigDecimal("100"));
                    BigDecimal puntajePonderado = new BigDecimal(promedio.toString()).multiply(ponderacion);
                    puntajePonderadoFinal = puntajePonderadoFinal.add(puntajePonderado);
                }
                puntajeDocente.puntajeMateriaList().add(
                        new PuntajeMateriaInfo(materia.materia(), materia.tipoDocente(), puntajePonderadoFinal)
                );
            }
        }
        return new Result(puntajeList);
    }

    private PuntajeDocenteInfo createPuntajeFrom(PersonaInfo personaInfo){
        return new  PuntajeDocenteInfo(personaInfo.id(), personaInfo.nombres(), personaInfo.apellidos(), personaInfo.ci(), new ArrayList<>());
    }

    public static record Query(Integer idPeriodo){}
    public static record Result(List<PuntajeDocenteInfo> puntajes){}
}
