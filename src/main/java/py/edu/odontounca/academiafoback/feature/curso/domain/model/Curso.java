package py.edu.odontounca.academiafoback.feature.curso.domain.model;

import py.edu.odontounca.academiafoback.feature.curso.domain.model.vo.OrdenSemestre;
import py.edu.odontounca.academiafoback.shared.validation.Assert;

public class Curso {
    private Integer id;
    private String descripcion;
    private Integer anho;
    private Integer nroSemestre;
    private OrdenSemestre ordenSemestre;

    private Curso(
            Integer id,
            String descripcion,
            Integer anho,
            Integer nroSemestre,
            OrdenSemestre ordenSemestre
    ){
        Assert.maxLength(descripcion, 100,"Curso: «descripcion» no debe ser nulo y debe terner máx. 100 carácteres.");
        Assert.notNull(anho, "Curso: «año» no debe ser nulo");
        Assert.notNull(ordenSemestre, "Curso: «semestre» no debe ser nulo");
        Assert.notNull(nroSemestre, "Curso: «nroSemestre» no debe ser nulo");
        Assert.isBetweenInclusive(nroSemestre, 0, 10, "Curso: «nroSemestre» debe estar entre 0 y 10");

        this.id = id;
        this.descripcion = descripcion;
        this.anho = anho;
        this.ordenSemestre = ordenSemestre;
        this.nroSemestre = nroSemestre;
    }

    public static Curso crear(String descripcion, Integer anho, Integer nroSemestre, OrdenSemestre ordenSemestre){
        return new Curso(null, descripcion, anho, nroSemestre, ordenSemestre);
    }

    public static Curso reconstruir(Integer id, String descripcion, Integer anho, Integer nroSemestre, OrdenSemestre ordenSemestre){
        Assert.notNull(id, "Curso: «id» no puede ser nulo");
        return new Curso(id, descripcion, anho, nroSemestre, ordenSemestre);
    }

    public Integer getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public Integer getAnho() { return anho; }
    public Integer getNroSemestre() { return nroSemestre; }
    public OrdenSemestre getOrdenSemestre() { return ordenSemestre; }
}
