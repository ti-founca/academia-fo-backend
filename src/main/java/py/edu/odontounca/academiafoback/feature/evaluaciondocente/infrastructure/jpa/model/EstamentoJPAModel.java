package py.edu.odontounca.academiafoback.feature.evaluaciondocente.infrastructure.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(schema = "evaluacion", name = "estamento")
@Getter @Setter @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EstamentoJPAModel {
   @Id
   private Integer id;

   @Column(length = 50, nullable = false)
   private String descripcion;

   @Column(name = "porcentaje_peso")
   private String porcentajePeso;
}
