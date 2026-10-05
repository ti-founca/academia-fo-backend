package py.edu.odontounca.academiafoback.feature.persona.domain.model;

import py.edu.odontounca.academiafoback.shared.validation.Assert;

public class TipoPersona {
    private Integer id;
    private String descripcion;

    public TipoPersona(Integer id, String descripcion) {
        Assert.notNull(descripcion, "TipoPersona: «descripcion» no puede ser nulo");
        Assert.maxLength(descripcion, 45, "TipoPersona: «descripcion» debe tener máx. 45 carácteres");
        this.id = id;
        this.descripcion = descripcion;
    }

    public static TipoPersona reconstruir(Integer id, String descripcion){
        Assert.notNull(id, "TipoPersona: «id» no puede ser nulo");
        return new TipoPersona(id, descripcion);
    }

    public static TipoPersona crear(String descripcion){
        return new TipoPersona(null, descripcion);
    }

    public Integer getId() { return id; }
    public String getDescripcion() { return descripcion; }
}
