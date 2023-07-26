package bf.gov.courrier.service;

import bf.gov.courrier.service.dto.TransitaireDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Transitaire.
 */
public interface TransitaireService {

    /**
     * Save a transitaire.
     *
     * @param transitaireDTO the entity to save
     * @return the persisted entity
     */
    TransitaireDTO save(TransitaireDTO transitaireDTO);

    /**
     * Get all the transitaires.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TransitaireDTO> findAll(Pageable pageable);


    /**
     * Get the "id" transitaire.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TransitaireDTO> findOne(Long id);

    /**
     * Delete the "id" transitaire.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
