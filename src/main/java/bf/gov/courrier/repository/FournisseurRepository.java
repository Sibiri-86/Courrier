package bf.gov.courrier.repository;

import bf.gov.courrier.domain.Fournisseur;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Fournisseur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
    List<Fournisseur> findByPaysIdAndDeletedFalse(Long paysId);
    Page<Fournisseur>findAllByDeletedFalse(Pageable pageable);

}
