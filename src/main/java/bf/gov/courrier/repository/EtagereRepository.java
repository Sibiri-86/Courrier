package bf.gov.courrier.repository;

import bf.gov.courrier.domain.Etagere;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Etagere entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtagereRepository extends JpaRepository<Etagere, Long> {
Page<Etagere>findAllByDeletedFalse(Pageable pageable);
}
