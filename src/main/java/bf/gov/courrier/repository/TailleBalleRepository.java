package bf.gov.courrier.repository;

import bf.gov.courrier.domain.TailleBalle;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TailleBalle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TailleBalleRepository extends JpaRepository<TailleBalle, Long> {

}
