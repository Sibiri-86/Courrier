package bf.gov.courrier.repository;

import bf.gov.courrier.domain.Client;
import bf.gov.courrier.domain.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TailleBalle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Page<Profile>findAllByDeletedFalse(Pageable pageable);

}
