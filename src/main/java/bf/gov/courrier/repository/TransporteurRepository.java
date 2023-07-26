package bf.gov.courrier.repository;

import bf.gov.courrier.domain.Transporteur;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Transporteur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransporteurRepository extends JpaRepository<Transporteur, Long> {

}
