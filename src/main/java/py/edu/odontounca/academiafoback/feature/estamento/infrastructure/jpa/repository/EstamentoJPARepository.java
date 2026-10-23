package py.edu.odontounca.academiafoback.feature.estamento.infrastructure.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import py.edu.odontounca.academiafoback.feature.estamento.infrastructure.jpa.model.EstamentoJPAModel;

public interface EstamentoJPARepository extends JpaRepository<EstamentoJPAModel, Integer>, JpaSpecificationExecutor<EstamentoJPAModel> {
}
