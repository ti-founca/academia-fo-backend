package py.edu.odontounca.academiafoback.feature.evaluaciondocente.reporte.domain.model;

import py.edu.odontounca.academiafoback.shared.validation.Assert;

public class Curso {
    private Integer id;
    private String descripcion;
    private Integer semestre;
    private Integer anho;

    private Curso(Integer id, String descripcion, Integer semestre, Integer anho) {
        Assert.notNull(id, "Curso: «id» no debe ser nulo");
        Assert.maxLength(descripcion, 100, "Curso: «descripcion» no debe ser nulo y no debe exceder los 100 carácteres");
        this.id = id;
        this.descripcion = descripcion;
        this.semestre = semestre;
        this.anho = anho;
    }

    public Integer getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public Integer getSemestre() { return semestre; }
    public Integer getAnho() { return anho; }

    public static Curso reconstruir(Integer id, String descripcion, Integer semestre, Integer anho){
        return new Curso(id, descripcion, semestre, anho);
    }
}
