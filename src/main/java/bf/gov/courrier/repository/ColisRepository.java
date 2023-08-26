package bf.gov.courrier.repository;

import bf.gov.courrier.domain.Agent;
import bf.gov.courrier.domain.Colis;
import bf.gov.courrier.domain.Statut;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Agent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ColisRepository extends JpaRepository<Colis, Long> {
    Page<Colis>findAllByDeletedFalseAndStatut(Pageable pageable, Statut statut);
    Page<Colis>findAllByDeletedFalse(Pageable pageable);
    Page<Colis>findAllByReceptionIdAndDeletedFalseAndStatut(Long receptionId,Pageable pageable, Statut statut);
    Page<Colis>findAllByReceptionIdAndDeletedFalse(Long receptionId,Pageable pageable);
    Page<Colis>findAllByEmballageIdAndDeletedFalseAndStatut(Long emballageId,Pageable pageable, Statut statut);
    List<Colis>findAllByEmballageIdAndDeletedFalseAndStatut(Long emballageId, Statut statut);
    List<Colis>findAllByReceptionIdAndDeletedFalse(Long receptionId);

}
