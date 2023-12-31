package bf.gov.courrier.repository;

import bf.gov.courrier.domain.Client;
import bf.gov.courrier.domain.Site;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Site entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
    List<Site> findByPaysIdAndDeletedFalse(Long paysId);
    Page<Site>findAllByDeletedFalse(Pageable pageable);
}
