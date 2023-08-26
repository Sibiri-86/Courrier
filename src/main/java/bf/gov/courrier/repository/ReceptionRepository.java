package bf.gov.courrier.repository;

import bf.gov.courrier.domain.Reception;
import java.time.LocalDate;
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
public interface ReceptionRepository extends JpaRepository<Reception, Long> {
    Page<Reception>findAllByDeletedFalse(Pageable pageable);
    Page<Reception>findAllByDateReceptionBetweenAndDeletedFalse(Pageable pageable, LocalDate date1,LocalDate date2);
    Page<Reception>findAllByDeletedFalseAndFournisseurIdAndDateReceptionBetween(Pageable pageable, Long fournisseurId, LocalDate date1,LocalDate date2);
    Page<Reception>findAllByDeletedFalseAndClientIdAndDateReceptionBetween(Pageable pageable, Long clientId, LocalDate date1,LocalDate date2);
    Page<Reception>findAllByDeletedFalseAndClientIdAndFournisseurIdAndDateReceptionBetween(Pageable pageable, Long clientId, Long fournisseurId, LocalDate date1,LocalDate date2);
}
