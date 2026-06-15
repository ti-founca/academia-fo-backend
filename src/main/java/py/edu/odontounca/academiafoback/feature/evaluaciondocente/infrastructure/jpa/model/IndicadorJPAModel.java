package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "evaluacion", name = "indicador")
@Getter @Setter @RequiredArgsConstructor
public class IndicadorJPAModel {
    @Id
    @Column(name = "ind_id")
    private Integer id;

    @Column(name = "ind_descripcion", length = 250)
    private String descripcion;

    @Column(name = "ind_orden")
    private Integer nroOrden;

    @ManyToOne
    @JoinColumn(name = "ind_formulario")
    private FormularioJPAModel formulario;
}
