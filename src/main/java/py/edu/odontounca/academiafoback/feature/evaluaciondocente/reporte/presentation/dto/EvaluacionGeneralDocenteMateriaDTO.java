package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.presentation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class EvaluacionGeneralDocenteMateriaDTO {
    private String docente;
    private String materia;
    private String codigoMateria;
    private String tipoDocente;
    private double promedioGeneral;
    private List<DetalleEvaluacionGeneralDocenteMateriaDTO> detalle;
}
