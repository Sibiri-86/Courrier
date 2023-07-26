package bf.gov.courrier.service;

import bf.gov.courrier.service.dto.RayonDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Rayon.
 */
public interface RayonService {

    /**
     * Save a rayon.
     *
     * @param rayonDTO the entity to save
     * @return the persisted entity
     */
    RayonDTO save(RayonDTO rayonDTO);

    /**
     * Get all the rayons.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RayonDTO> findAll(Pageable pageable);


    /**
     * Get the "id" rayon.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RayonDTO> findOne(Long id);

    /**
     * Delete the "id" rayon.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
