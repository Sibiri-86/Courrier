package bf.gov.courrier.service;

import bf.gov.courrier.service.dto.ClientDTO;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Client.
 */
public interface ClientService {

    /**
     * Save a client.
     *
     * @param clientDTO the entity to save
     * @return the persisted entity
     */
    ClientDTO save(ClientDTO clientDTO);

    /**
     * Get all the clients.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ClientDTO> findAll(Pageable pageable);


    /**
     * Get the "id" client.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ClientDTO> findOne(Long id);

    /**
     * Delete the "id" client.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
     List<ClientDTO> findAllByPays(Long paysId);
}
