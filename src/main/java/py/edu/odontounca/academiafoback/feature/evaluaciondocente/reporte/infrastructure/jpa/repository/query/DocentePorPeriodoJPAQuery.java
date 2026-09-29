package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.repository.query;
import py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.infrastructure.jpa.model.PersonaJPAModel;

import java.util.List;

public interface DocentePorPeriodoJPAQuery {
    public List<PersonaJPAModel> obtenerDocentesPorPeriodo(Integer idPeriodo);
}
