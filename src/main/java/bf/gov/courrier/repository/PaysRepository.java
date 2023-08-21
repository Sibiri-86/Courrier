package bf.gov.courrier.repository;

import bf.gov.courrier.domain.Pays;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Pays entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaysRepository extends JpaRepository<Pays, Long> {

    Page<Pays>findAllByDeletedFalse(Pageable pageable);
    
}
