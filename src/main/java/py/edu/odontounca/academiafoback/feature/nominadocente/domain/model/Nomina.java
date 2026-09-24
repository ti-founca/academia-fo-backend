package py.edu.odontounca.academiafoback.feature.nominadocente.domain.model;

import py.edu.odontounca.academiafoback.shared.validation.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Nomina {
    private Integer id;
    private String nombre;
    private PeriodoLectivo periodoLectivo;
    private PlanCurricular planCurricular;
    private List<NominaDetalle> detalles;

    private Nomina(Integer id, String nombre, PeriodoLectivo periodoLectivo, PlanCurricular planCurricular, List<NominaDetalle> detalles) {
        Assert.maxLength(nombre, 255, "Nomina: «nombre» no debe ser nulo y debe tener máx. 255 carácteres");
        Assert.notNull(periodoLectivo, "Nomina: «periodoLectivo» no debe ser nulo");
        Assert.notNull(planCurricular, "Nomina: «planCurricular» no debe ser nulo");
        Assert.notNull(detalles, "Nomina: «detalles» no debe ser nulo");
        this.id = id;
        this.nombre = nombre;
        this.periodoLectivo = periodoLectivo;
        this.planCurricular = planCurricular;
        this.detalles = new ArrayList<>(detalles);
    }

    public static Nomina crear(String nombre, PeriodoLectivo periodoLectivo, PlanCurricular planCurricular){
        return new Nomina(null, nombre, periodoLectivo, planCurricular, List.of());
    }

    public static Nomina reconstruir(Integer id, String nombre, PeriodoLectivo periodoLectivo, PlanCurricular planCurricular, List<NominaDetalle> detalles){
        Assert.notNull(id, "Nomina: «id» no puede ser nulo");
        return new Nomina(id, nombre, periodoLectivo, planCurricular, detalles);
    }

    public NominaDetalle agregarDetalle(Docente docente, Materia materia, TipoDocente tipoDocente){
        NominaDetalle detalle = NominaDetalle.crear(docente, materia, tipoDocente);
        this.detalles.add(detalle);
        return detalle;
    }

    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public PeriodoLectivo getPeriodoLectivo() { return periodoLectivo; }
    public PlanCurricular getPlanCurricular() { return planCurricular; }
    public List<NominaDetalle> getDetalles() { return Collections.unmodifiableList(detalles); }
}
