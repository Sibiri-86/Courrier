package bf.gov.courrier.repository;

import bf.gov.courrier.domain.Client;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Client entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByPaysIdAndDeletedFalse(Long paysId);
    Page<Client>findAllByDeletedFalse(Pageable pageable);
}
