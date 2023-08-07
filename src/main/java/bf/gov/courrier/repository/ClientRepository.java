package bf.gov.courrier.repository;

import bf.gov.courrier.domain.Client;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Client entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByPaysId(Long paysId);
}
