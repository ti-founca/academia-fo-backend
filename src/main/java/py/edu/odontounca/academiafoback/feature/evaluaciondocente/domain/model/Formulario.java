package py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model;

import py.edu.odontounca.academiafoback.shared.validation.Assert;

import java.time.LocalDateTime;
import java.util.*;

public class Formulario {
    private Integer id;
    private String descripcion;
    private LocalDateTime fechaApertura;
    private LocalDateTime fechaCierre;
    private boolean activo;
    private List<Indicador> indicadores = new ArrayList<>();

    private Formulario(Integer id, String descripcion, LocalDateTime fechaApertura, LocalDateTime fechaCierre, boolean activo, List<Indicador> indicadores) {
        Assert.maxLength(descripcion, 100, "Formulario: «descripción» no debe ser nulo y no debe exceder 100 carácteres.");
        Assert.notNull(id, "«id» no debe ser nulo");
        Assert.isAfter(fechaCierre, fechaApertura, "Formulario: «fechaCierre» no debe ser nulo y debe ser posterior a «fechaApertura»");
        Assert.isBefore(fechaApertura, fechaCierre, "Formulario: «fechaApertura» no debe ser nulo y debe ser anterior a «fechaCierre»");
        Assert.notNull(indicadores, "Formulario: «indicadores» no puede ser nulo");

        this.id = id;
        this.descripcion = descripcion;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
        this.activo = activo;
        this.indicadores.addAll(indicadores);
    }

    public Integer getId() {
        return id;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public LocalDateTime getFechaApertura() {
        return fechaApertura;
    }
    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }
    public boolean isActivo() {
        return activo;
    }
    public List<Indicador> getIndicadores(){
        return Collections.unmodifiableList(this.indicadores);
    }

    public void agregarIndicador(Indicador indicador){
        this.indicadores.add(indicador);
    }

    public Formulario reconstruir(Integer id, String descripcion, LocalDateTime fechaApertura, LocalDateTime fechaCierre, boolean activo, List<Indicador> indicadores){
        return new Formulario(id, descripcion, fechaApertura, fechaCierre, activo, indicadores);
    }
}
