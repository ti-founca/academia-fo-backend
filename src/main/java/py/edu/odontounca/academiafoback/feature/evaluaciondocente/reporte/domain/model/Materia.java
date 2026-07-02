package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model;

import py.edu.odontounca.academiafoback.shared.validation.Assert;

public class Materia {
    private Integer id;
    private String descripcion;
    private String codigo;

    private Materia(Integer id, String descripcion, String codigo) {
        Assert.notNull(id, "Materia: «id» no debe ser nulo");
        Assert.maxLength(descripcion, 120, "Materia: «descripcion» no debe ser nulo y no debe exceder los 120 carácteres");
        Assert.notNull(codigo, "Materia: «codigo» no debe ser nulo");
        this.id = id;
        this.descripcion = descripcion;
        this.codigo = codigo;
    }

    public Integer getId() {
        return id;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getCodigo() {
        return codigo;
    }

    public static Materia reconstruir(Integer id, String descripcion, String codigo){
        return new Materia(id, descripcion, codigo);
    }
}
