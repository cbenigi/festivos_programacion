package co.edu.festivos.infrastructure.persistence.repository;
import co.edu.festivos.infrastructure.persistence.entity.TipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TipoJpaRepository extends JpaRepository<TipoEntity, Integer> {}
