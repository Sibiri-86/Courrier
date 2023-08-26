package bf.gov.courrier.repository;

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
public interface EmballageRepository extends JpaRepository<Emballage, Long> {
    Page<Emballage> findAllByDeletedFalseAndChargementIdIsNull(Pageable pageable);
    Page<Emballage> findAllByChargementIdAndDeletedFalse(Long chargementId, Pageable pageable);
    Page<Emballage> findAllByDeletedFalse(Pageable pageable);

}
