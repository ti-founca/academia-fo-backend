package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model;

import py.edu.odontounca.academiafoback.shared.validation.Assert;

public class TipoDocente {
    private Integer id;
    private String descripcion;

    private TipoDocente(Integer id, String descripcion) {
        Assert.notNull(id, "TipoDocente: «id» no puede ser nulo");
        Assert.maxLength(descripcion, 50, "TipoDocente: «decripcion no puede ser nulo y tener máx. 50 carácteres»");
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() { return id; }
    public String getDescripcion() { return descripcion; }

    public static TipoDocente reconstruir(Integer id, String descripcion){
        return new TipoDocente(id, descripcion);
    }
}
