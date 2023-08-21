package bf.gov.courrier.repository;

import bf.gov.courrier.domain.Rayon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Rayon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RayonRepository extends JpaRepository<Rayon, Long> {
    Page<Rayon>findAllByDeletedFalse(Pageable pageable);

}
