package co.edu.festivos.infrastructure.persistence.repository;
import co.edu.festivos.infrastructure.persistence.entity.PaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PaisJpaRepository extends JpaRepository<PaisEntity, Integer> {}
