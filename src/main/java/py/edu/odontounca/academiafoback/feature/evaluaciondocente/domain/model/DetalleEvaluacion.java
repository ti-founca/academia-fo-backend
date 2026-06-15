package py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model;

import py.edu.odontounca.academiafoback.shared.validation.Assert;

public class DetalleEvaluacion {
    private Integer id;
    private Integer puntaje;
    private Indicador indicador;

    private DetalleEvaluacion(Integer id, Integer puntaje, Indicador indicador) {
        Assert.notNull(id, "DetalleEvaluacion: «id» no debe ser nulo");
        Assert.isBetweenInclusive(puntaje, 1, 5, "DetalleEvaluacion: «puntaje» no debe ser nulo y debe estar entre 1 y 5");
        Assert.notNull(indicador, "DetalleEvaluacion: «indicador» no debe ser nulo");
        this.id = id;
        this.puntaje = puntaje;
        this.indicador = indicador;
    }

    public Integer getId() {
        return id;
    }
    public Integer getPuntaje() {
        return puntaje;
    }
    public Indicador getIndicador() {
        return indicador;
    }
}
