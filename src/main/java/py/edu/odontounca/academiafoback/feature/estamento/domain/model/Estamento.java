package py.edu.odontounca.academiafoback.feature.estamento.domain.model;

import py.edu.odontounca.academiafoback.shared.validation.Assert;

import java.math.BigDecimal;

public class Estamento {
    private Integer id;
    private String descripcion;
    private BigDecimal porcentajePeso;

    public Estamento(Integer id, String descripcion, BigDecimal porcentajePeso) {
        Assert.maxLength(descripcion, 50, "Estamento: «descripcion» no debe ser nulo y debe tener máx. 50 carácteres");
        Assert.isBetweenInclusive(porcentajePeso, "0.0", "100.0", "Estamento: «porcentajePeso» debe estar entre 0 y 100");
        this.id = id;
        this.descripcion = descripcion;
        this.porcentajePeso = porcentajePeso;
    }

    public static Estamento reconstruir(Integer id, String descripcion, BigDecimal porcentajePeso){
        Assert.notNull(id, "Estamento: «id» no debe ser nulo");
        return new Estamento(id, descripcion, porcentajePeso);
    }

    public static Estamento crear(String descripcion, BigDecimal porcentajePeso){
        return new Estamento(null, descripcion, porcentajePeso);
    }

    public Integer getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public BigDecimal getPorcentajePeso() { return porcentajePeso; }
}
