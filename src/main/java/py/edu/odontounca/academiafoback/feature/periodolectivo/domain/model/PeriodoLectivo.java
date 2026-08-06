package py.edu.odontounca.academiafoback.feature.periodolectivo.domain.model;

import py.edu.odontounca.academiafoback.feature.periodolectivo.domain.model.vo.NumeroSemestre;
import py.edu.odontounca.academiafoback.feature.periodolectivo.domain.model.vo.TipoPeriodoLectivo;
import py.edu.odontounca.academiafoback.shared.validation.Assert;

import java.time.LocalDate;

public class PeriodoLectivo {
    private Integer id;
    private String descripcion;
    private Integer anho;
    private boolean cerrado;
    private TipoPeriodoLectivo tipo;
    private NumeroSemestre semestre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public PeriodoLectivo(
            Integer id,
            String descripcion,
            Integer anho,
            boolean cerrado,
            TipoPeriodoLectivo tipo,
            NumeroSemestre semestre,
            LocalDate fechaInicio,
            LocalDate fechaFin
    ) {
        Assert.maxLength(descripcion, 45, "PeriodoLectivo: «descripcion» no puede ser nulo y debe tener máx. 45 carácteres");
        Assert.notNull(anho, "PeriodoLectivo: «anho» no debe ser nulo");
        Assert.notNull(tipo, "PeriodoLectivo: «tipoPeriodoLectivo» no puede ser nulo");
        Assert.notNull(semestre, "PeriodoLectivo: «nroSemestre» no puede ser nulo");
        if(fechaInicio != null && fechaFin == null) throw new IllegalArgumentException("PeriodoLectivo: «fechaFin» no puede ser nulo");
        if(fechaFin != null && fechaInicio == null) throw new IllegalArgumentException("PeriodoLectivo: «fechaInicio» no puede ser nulo");
        if(fechaInicio != null && fechaFin != null){
            Assert.isBefore(fechaInicio, fechaFin, "PeriodoLectivo: «fechaInicio» no puede ser posterior a la fecha de fin");
            Assert.isAfter(fechaFin, fechaInicio, "PeriodoLectivo: «fechaFin» no puede ser anterior a la fecha de inicio");
        }
        this.id = id;
        this.descripcion = descripcion;
        this.anho = anho;
        this.cerrado = cerrado;
        this.tipo = tipo;
        this.semestre = semestre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public static PeriodoLectivo reconstruir(
            Integer id,
            String descripcion,
            Integer anho,
            boolean cerrado,
            TipoPeriodoLectivo tipo,
            NumeroSemestre semestre,
            LocalDate fechaInicio,
            LocalDate fechaFin
    ){
        Assert.notNull(id, "PeriodoLectivo: «id» no puede ser nulo");
        return new PeriodoLectivo(id, descripcion, anho, cerrado, tipo, semestre, fechaInicio, fechaFin);
    }

    public static PeriodoLectivo crear(
            String descripcion,
            Integer anho,
            boolean cerrado,
            TipoPeriodoLectivo tipo,
            NumeroSemestre semestre,
            LocalDate fechaInicio,
            LocalDate fechaFin
    ){
        return new PeriodoLectivo(null, descripcion, anho, cerrado, tipo, semestre, fechaInicio, fechaFin);
    }

    public Integer getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public Integer getAnho() { return anho; }
    public boolean isCerrado() { return cerrado; }
    public TipoPeriodoLectivo getTipo() { return tipo; }
    public NumeroSemestre getSemestre() { return semestre; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
}
