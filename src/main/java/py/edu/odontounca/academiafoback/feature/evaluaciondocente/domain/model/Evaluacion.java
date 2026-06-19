package py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model;

import py.edu.odontounca.academiafoback.shared.validation.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Evaluacion {
    private Integer id;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private boolean terminado;
    private Formulario formulario;
    private Materia materia;
    private Persona evaluado;
    private Persona evaluador;
    private List<DetalleEvaluacion> detalles = new ArrayList<>();

    private Evaluacion(
            Integer id,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin,
            boolean terminado,
            Formulario formulario,
            Materia materia,
            Persona evaluado,
            Persona evaluador,
            List<DetalleEvaluacion> detalles
    ) {
        Assert.notNull(id, "Evaluacion: «id» no puede ser nulo");
        Assert.notNull(fechaInicio, "Evaluacion: «fechaInicio» no puede ser nulo");
        if(terminado && fechaFin == null) throw new IllegalArgumentException("Evaluacion: La evaluación está terminada por lo que «fechaFin» no debe ser nulo");
        if(fechaFin != null) Assert.isAfter(fechaFin, fechaInicio, "«fechaFin» no debe ser posterior a fechaInicio");
        Assert.notNull(formulario, "Evaluacion: «formulario» no debe ser nulo");
        Assert.notNull(materia, "Evaluacion: «materia» no debe ser nulo");
        Assert.notNull(evaluado, "Evaluacion: «evaluado» no debe ser nulo");
        Assert.notNull(evaluador, "Evaluacion: «evaluador» no debe ser nulo");
        Assert.notNull(detalles, "Evaluacion: «detalles» no debe ser nulo");

        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.terminado = terminado;
        this.formulario = formulario;
        this.materia = materia;
        this.evaluado = evaluado;
        this.evaluador = evaluador;
        this.detalles.addAll(detalles);
    }

    public Integer getId() {
        return id;
    }
    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }
    public LocalDateTime getFechaFin() {
        return fechaFin;
    }
    public boolean isTerminado() {
        return terminado;
    }
    public Formulario getFormulario() {
        return formulario;
    }
    public Materia getMateria() {
        return materia;
    }
    public Persona getEvaluado() {
        return evaluado;
    }
    public Persona getEvaluador() {
        return evaluador;
    }
    public List<DetalleEvaluacion> getDetalles() {
        return Collections.unmodifiableList(detalles);
    }

    public static Evaluacion reconstruir(
            Integer id,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin,
            boolean terminado,
            Formulario formulario,
            Materia materia,
            Persona evaluado,
            Persona evaluador,
            List<DetalleEvaluacion> detalles
    ){
        return new Evaluacion(id, fechaInicio,fechaFin, terminado, formulario, materia, evaluado, evaluador, detalles);
    }
}
