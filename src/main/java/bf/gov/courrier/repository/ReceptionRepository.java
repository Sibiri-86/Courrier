package bf.gov.courrier.repository;

import bf.gov.courrier.domain.Reception;
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
public interface ReceptionRepository extends JpaRepository<Reception, Long> {
    Page<Reception>findAllByDeletedFalse(Pageable pageable);
}
