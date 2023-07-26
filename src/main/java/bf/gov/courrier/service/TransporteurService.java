package bf.gov.courrier.service;

import bf.gov.courrier.service.dto.TransporteurDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Transporteur.
 */
public interface TransporteurService {

    /**
     * Save a transporteur.
     *
     * @param transporteurDTO the entity to save
     * @return the persisted entity
     */
    TransporteurDTO save(TransporteurDTO transporteurDTO);

    /**
     * Get all the transporteurs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TransporteurDTO> findAll(Pageable pageable);


    /**
     * Get the "id" transporteur.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TransporteurDTO> findOne(Long id);

    /**
     * Delete the "id" transporteur.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
