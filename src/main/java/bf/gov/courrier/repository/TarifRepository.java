package bf.gov.courrier.repository;

import bf.gov.courrier.domain.Client;
import bf.gov.courrier.domain.Tarif;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Tarif entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TarifRepository extends JpaRepository<Tarif, Long> {
    Page<Tarif>findAllByDeletedFalse(Pageable pageable);

}
