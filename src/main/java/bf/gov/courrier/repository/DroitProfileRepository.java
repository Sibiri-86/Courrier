package bf.gov.courrier.repository;

import bf.gov.courrier.domain.Agent;
import bf.gov.courrier.domain.DroitProfile;
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
public interface DroitProfileRepository extends JpaRepository<DroitProfile, Long> {
    List<DroitProfile> findByProfileIdAndDeletedFalse(Long profileId);
    Page<DroitProfile>findAllByDeletedFalse(Pageable pageable);
}
