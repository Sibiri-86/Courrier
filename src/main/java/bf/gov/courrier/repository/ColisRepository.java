package bf.gov.courrier.repository;

import bf.gov.courrier.domain.Agent;
import bf.gov.courrier.domain.Colis;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Agent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ColisRepository extends JpaRepository<Colis, Long> {
    Page<Agent>findAllByDeletedFalse(Pageable pageable);
}
