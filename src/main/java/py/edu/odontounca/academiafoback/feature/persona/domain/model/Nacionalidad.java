package py.edu.odontounca.academiafoback.feature.persona.domain.model;

import py.edu.odontounca.academiafoback.shared.validation.Assert;

public class Nacionalidad {
    private Integer id;
    private String descripcion;

    public Nacionalidad(Integer id, String descripcion) {
        Assert.notNull(descripcion, "Nacionalidad: «descripcion» no puede ser nulo");
        Assert.maxLength(descripcion, 45, "Nacionalidad: «descripcion» debe tener máx. 45 carácteres");
        this.id = id;
        this.descripcion = descripcion;
    }

    public static Nacionalidad reconstruir(Integer id, String descripcion){
        Assert.notNull(id, "Nacionalidad: «id» no puede ser nulo");
        return new Nacionalidad(id, descripcion);
    }

    public static Nacionalidad crear(String descripcion){
        return new Nacionalidad(null, descripcion);
    }

    public Integer getId() { return id; }
    public String getDescripcion() { return descripcion; }
}
