package aurelion.repository;

import aurelion.model.WildflyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<WildflyModel, Long> {
} 