package py.edu.odontounca.academiafoback.feature.persona.domain.model;

import py.edu.odontounca.academiafoback.shared.validation.Assert;

public class Ciudad {
    private Integer id;
    private String descripcion;

    public Ciudad(Integer id, String descripcion) {
        Assert.notNull(descripcion, "Ciudad: «descripcion» no puede ser nulo");
        Assert.maxLength(descripcion, 45, "Ciudad: «descripcion» debe tener máx. 45 carácteres");
        this.id = id;
        this.descripcion = descripcion;
    }

    public static Ciudad reconstruir(Integer id, String descripcion){
        Assert.notNull(id, "Ciudad: «id» no puede ser nulo");
        return new Ciudad(id, descripcion);
    }

    public static Ciudad crear(String descripcion){
        return new Ciudad(null, descripcion);
    }

    public Integer getId() { return id; }
    public String getDescripcion() { return descripcion; }
}
