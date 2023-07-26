package bf.gov.courrier.repository;

import bf.gov.courrier.domain.TypeTransporteur;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TypeTransporteur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeTransporteurRepository extends JpaRepository<TypeTransporteur, Long> {

}
