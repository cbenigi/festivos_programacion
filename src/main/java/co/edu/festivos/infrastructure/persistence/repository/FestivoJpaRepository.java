package co.edu.festivos.infrastructure.persistence.repository;
import co.edu.festivos.infrastructure.persistence.entity.FestivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface FestivoJpaRepository extends JpaRepository<FestivoEntity, Integer> {
    List<FestivoEntity> findByPaisId(Integer paisId);
}
