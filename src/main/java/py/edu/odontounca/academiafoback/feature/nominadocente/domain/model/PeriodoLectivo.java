package py.edu.odontounca.academiafoback.feature.nominadocente.domain.model;

import py.edu.odontounca.academiafoback.shared.validation.Assert;

public class PeriodoLectivo {
    private Integer id;
    private String descripcion;

    private PeriodoLectivo(Integer id, String descripcion) {
        Assert.maxLength(descripcion, 45, "PeriodoLectivo: «descripcion» no debe ser nulo y debe tener máx. 45 carácteres");
        this.id = id;
        this.descripcion = descripcion;
    }

    public static PeriodoLectivo crear(String descripcion){
        return new PeriodoLectivo(null, descripcion);
    }

    public static PeriodoLectivo reconstruir(Integer id, String descripcion){
        Assert.notNull(id, "PeriodoLectivo: «id» no puede ser nulo");
        return new PeriodoLectivo(id, descripcion);
    }

    public Integer getId() { return id; }
    public String getDescripcion() { return descripcion; }
}
