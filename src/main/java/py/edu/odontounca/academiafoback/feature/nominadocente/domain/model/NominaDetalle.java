package py.edu.odontounca.academiafoback.feature.nominadocente.domain.model;

import py.edu.odontounca.academiafoback.shared.validation.Assert;

public class NominaDetalle {
    private Docente docente;
    private Materia materia;
    private TipoDocente tipoDocente;

    private NominaDetalle(Docente docente, Materia materia, TipoDocente tipoDocente) {
        Assert.notNull(docente, "NominaDetalle: «docente» no debe ser nulo");
        Assert.notNull(materia, "NominaDetalle: «materia» no debe ser nulo");
        Assert.notNull(tipoDocente, "NominaDetalle: «tipoDocente» no debe ser nulo");
        this.docente = docente;
        this.materia = materia;
        this.tipoDocente = tipoDocente;
    }

    public static NominaDetalle crear(Docente docente, Materia materia, TipoDocente tipoDocente){
        return new NominaDetalle(docente, materia, tipoDocente);
    }

    public static NominaDetalle reconstruir(Docente docente, Materia materia, TipoDocente tipoDocente){
        return new NominaDetalle(docente, materia, tipoDocente);
    }

    public Docente getDocente() { return docente; }
    public Materia getMateria() { return materia; }
    public TipoDocente getTipoDocente() { return tipoDocente; }
}
