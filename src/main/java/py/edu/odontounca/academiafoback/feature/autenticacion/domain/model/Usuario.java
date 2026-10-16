package py.edu.odontounca.academiafoback.feature.autenticacion.domain.model;

import py.edu.odontounca.academiafoback.shared.validation.Assert;

public class Usuario {
    private Integer id;
    private String nombre;
    private String apellido;
    private String correo;
    private String claveCifrada;

    public Usuario(Integer id, String nombre, String apellido, String correo, String claveCifrada) {
        Assert.maxLength(nombre, 45, "Usuario: «nombre» debe tener máx. 45 carácteres");
        Assert.maxLength(apellido, 45, "Usuario: «apellido» debe tener máx. 45 carácteres");
        Assert.maxLength(correo, 90, "Usuario: «correo» debe tener máx. 90 carácteres");
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.claveCifrada = claveCifrada;
    }

    public static Usuario reconstruir(Integer id, String nombre, String apellido, String correo, String claveCifrada){
        Assert.notNull(id, "Usuario: «id» no puede ser nulo");
        return new Usuario(id, nombre, apellido, correo, claveCifrada);
    }

    public static Usuario crear(String nombre, String apellido, String correo, String claveCifrada){
        return new Usuario(null, nombre, apellido, correo, claveCifrada);
    }

    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getCorreo() { return correo; }
    public String getClaveCifrada() { return claveCifrada; }
}
