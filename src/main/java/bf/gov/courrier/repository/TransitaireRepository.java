package bf.gov.courrier.repository;

import bf.gov.courrier.domain.Transitaire;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Transitaire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransitaireRepository extends JpaRepository<Transitaire, Long> {
    List<Transitaire> findByPaysId(Long paysId);
}
