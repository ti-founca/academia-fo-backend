package py.edu.odontounca.academiafoback.feature.nominadocente.domain.model;

import py.edu.odontounca.academiafoback.shared.validation.Assert;

public class Docente {
    private Integer id;
    private String nombres;
    private String apellidos;

    private Docente(Integer id, String nombres, String apellidos) {
        Assert.maxLength(nombres, 50, "Docente: «nombres» no debe ser nulo y debe tener máx. 50 carácteres");
        Assert.maxLength(apellidos, 50, "Docente: «apellidos» no debe ser nulo y debe tener máx. 50 carácteres");
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public static Docente crear(String nombres, String apellidos){
        return new Docente(null, nombres, apellidos);
    }

    public static Docente reconstruir(Integer id, String nombres, String apellidos){
        Assert.notNull(id, "Docente: «id» no puede ser nulo");
        return new Docente(id, nombres, apellidos);
    }

    public Integer getId() { return id; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
}
