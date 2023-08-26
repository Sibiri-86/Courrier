package bf.gov.courrier.repository;

import bf.gov.courrier.domain.Chargement;
import bf.gov.courrier.domain.Emballage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Emballage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChargementRepository extends JpaRepository<Chargement, Long> {
    Page<Chargement> findAllByDeletedFalse(Pageable pageable);

}
