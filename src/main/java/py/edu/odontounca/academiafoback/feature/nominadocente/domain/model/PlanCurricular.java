package py.edu.odontounca.academiafoback.feature.nominadocente.domain.model;

import py.edu.odontounca.academiafoback.shared.validation.Assert;

public class PlanCurricular {
    private Integer id;
    private String descripcion;

    private PlanCurricular(Integer id, String descripcion) {
        Assert.maxLength(descripcion, 45, "PlanCurricular: «descripcion» no debe ser nulo y debe tener máx. 45 carácteres");
        this.id = id;
        this.descripcion = descripcion;
    }

    public static PlanCurricular crear(String descripcion){
        return new PlanCurricular(null, descripcion);
    }

    public static PlanCurricular reconstruir(Integer id, String descripcion){
        Assert.notNull(id, "PlanCurricular: «id» no puede ser nulo");
        return new PlanCurricular(id, descripcion);
    }

    public Integer getId() { return id; }
    public String getDescripcion() { return descripcion; }
}
