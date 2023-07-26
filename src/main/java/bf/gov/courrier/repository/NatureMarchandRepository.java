package bf.gov.courrier.repository;

import bf.gov.courrier.domain.NatureMarchand;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NatureMarchand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NatureMarchandRepository extends JpaRepository<NatureMarchand, Long> {

}
