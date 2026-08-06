package py.edu.odontounca.academiafoback.feature.periodolectivo.infrastructure.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(schema = "public", name = "periodoslectivos")
@Getter @Setter
public class PeriodoLectivoJPAModel {
    @Id
    @Column(name = "pel_id")
    private Integer id;

    @Column(name = "pel_descri", length = 45, nullable = false)
    private String descripcion;

    @Column(name = "pel_anho", nullable = false)
    private Integer anho;

    @Column(name = "pel_cerrado", nullable = false)
    private boolean cerrado;

    @Column(name = "pel_seme", nullable = false)
    private Integer semestre;

    @Column(name = "pel_tipo", length = 1)
    private String tipo;

    @Column(name = "pel_fecha_desde")
    private LocalDate fechaInicio;

    @Column(name = "pel_fecha_hasta")
    private LocalDate fechaFin;
}
