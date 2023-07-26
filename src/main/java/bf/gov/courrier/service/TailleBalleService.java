package bf.gov.courrier.service;

import bf.gov.courrier.service.dto.TailleBalleDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TailleBalle.
 */
public interface TailleBalleService {

    /**
     * Save a tailleBalle.
     *
     * @param tailleBalleDTO the entity to save
     * @return the persisted entity
     */
    TailleBalleDTO save(TailleBalleDTO tailleBalleDTO);

    /**
     * Get all the tailleBalles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TailleBalleDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tailleBalle.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TailleBalleDTO> findOne(Long id);

    /**
     * Delete the "id" tailleBalle.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
