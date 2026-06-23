package py.edu.odontounca.academiafoback.feature.evaluaciondocente.application.dto.info;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public record EvaluacionInfo(
        Integer id,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin,
        boolean terminado,
        FormularioInfo formulario,
        MateriaInfo materia,
        PersonaInfo evaluado,
        PersonaInfo evaluador,
        List<DetalleEvaluacionInfo> detalles
) { }
