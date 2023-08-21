package bf.gov.courrier.repository;

import bf.gov.courrier.domain.Agent;
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
public interface AgentRepository extends JpaRepository<Agent, Long> {
    List<Agent> findBySiteIdAndDeletedFalse(Long siteId);
    Page<Agent>findAllByDeletedFalse(Pageable pageable);
}
