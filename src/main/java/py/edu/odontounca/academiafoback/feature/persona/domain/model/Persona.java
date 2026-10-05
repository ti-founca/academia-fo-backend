package py.edu.odontounca.academiafoback.feature.persona.domain.model;

import py.edu.odontounca.academiafoback.feature.persona.domain.model.vo.EstadoCivil;
import py.edu.odontounca.academiafoback.feature.persona.domain.model.vo.Sexo;
import py.edu.odontounca.academiafoback.shared.validation.Assert;

import java.time.LocalDate;

public class Persona {
    private Integer id;
    private String nombres;
    private String apellidos;
    private String ci;
    private String email;
    private Sexo sexo;
    private EstadoCivil estadoCivil;
    private LocalDate fechaNacimiento;
    private TipoPersona tipoPersona;
    private Ciudad ciudad;
    private Nacionalidad nacionalidad;

    public Persona(
            Integer id,
            String nombres,
            String apellidos,
            String ci,
            String email,
            Sexo sexo,
            EstadoCivil estadoCivil,
            LocalDate fechaNacimiento,
            TipoPersona tipoPersona,
            Ciudad ciudad,
            Nacionalidad nacionalidad
    ) {
        Assert.notNull(nombres, "Persona: «nombres» no puede ser nulo");
        Assert.maxLength(nombres, 50, "Persona: «nombres» debe tener máx. 50 carácteres");
        Assert.notNull(apellidos, "Persona: «apellidos» no puede ser nulo");
        Assert.maxLength(apellidos, 50, "Persona: «apellidos» debe tener máx. 50 carácteres");
        Assert.notNull(ci, "Persona: «ci» no puede ser nulo");
        Assert.maxLength(ci, 15, "Persona: «ci» debe tener máx. 15 carácteres");
        Assert.maxLength(email, 60, "Persona: «email» debe tener máx. 60 carácteres");
        Assert.notNull(sexo, "Persona: «sexo» no puede ser nulo");
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ci = ci;
        this.email = email;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoPersona = tipoPersona;
        this.ciudad = ciudad;
        this.nacionalidad = nacionalidad;
    }

    public static Persona reconstruir(
            Integer id,
            String nombres,
            String apellidos,
            String ci,
            String email,
            Sexo sexo,
            EstadoCivil estadoCivil,
            LocalDate fechaNacimiento,
            TipoPersona tipoPersona,
            Ciudad ciudad,
            Nacionalidad nacionalidad
    ){
        Assert.notNull(id, "Persona: «id» no puede ser nulo");
        return new Persona(id, nombres, apellidos, ci, email, sexo, estadoCivil, fechaNacimiento, tipoPersona, ciudad, nacionalidad);
    }

    public static Persona crear(
            String nombres,
            String apellidos,
            String ci,
            String email,
            Sexo sexo,
            EstadoCivil estadoCivil,
            LocalDate fechaNacimiento,
            TipoPersona tipoPersona,
            Ciudad ciudad,
            Nacionalidad nacionalidad
    ){
        return new Persona(null, nombres, apellidos, ci, email, sexo, estadoCivil, fechaNacimiento, tipoPersona, ciudad, nacionalidad);
    }

    public Integer getId() { return id; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getCi() { return ci; }
    public String getEmail() { return email; }
    public Sexo getSexo() { return sexo; }
    public EstadoCivil getEstadoCivil() { return estadoCivil; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public TipoPersona getTipoPersona() { return tipoPersona; }
    public Ciudad getCiudad() { return ciudad; }
    public Nacionalidad getNacionalidad() { return nacionalidad; }
}
