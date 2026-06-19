package py.edu.odontounca.academiafoback.feature.evaluaciondocente.domain.model;

import py.edu.odontounca.academiafoback.shared.validation.Assert;

public class Persona {
    private Integer id;
    private String nombres;
    private String apellidos;
    private String ci;

    private Persona(Integer id, String nombres, String apellidos, String ci) {
        Assert.maxLength(nombres, 50, "Evaluado: «nombres» no puede ser nulo y no debe exceder 50 carácteres.");
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ci = ci;
    }

    public Integer getId() {
        return id;
    }
    public String getNombres() {
        return nombres;
    }
    public String getApellidos() {
        return apellidos;
    }
    public String getCi() {
        return ci;
    }

    public static Persona reconstruir(Integer id, String nombres, String apellidos, String ci){
        return new Persona(id, nombres, apellidos, ci);
    }
}
