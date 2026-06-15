package py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model;

import py.edu.odontounca.academiafoback.shared.validation.Assert;

public class Indicador {
    private Integer id;
    private Integer nroOrden;
    private String descripcion;

    private Indicador(Integer id, Integer nroOrden, String descripcion) {
        Assert.notNull(id, "Indicador: «id» no debe ser nulo");
        Assert.notNull(nroOrden, "Indicador: «nroOrden» no debe ser nulo");
        Assert.maxLength(descripcion, 250, "Indicador: «descripcion» no debe ser nulo y no debe exceder los  255 carácteres");

        this.id = id;
        this.nroOrden = nroOrden;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }
    public Integer getNroOrden() {
        return nroOrden;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public static Indicador reconstruir(Integer id, Integer nroOrden, String descripcion){
        return new Indicador(id, nroOrden, descripcion);
    }
}
