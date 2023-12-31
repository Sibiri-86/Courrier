package bf.gov.courrier.service;

import bf.gov.courrier.service.dto.EtagereDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Etagere.
 */
public interface EtagereService {

    /**
     * Save a etagere.
     *
     * @param etagereDTO the entity to save
     * @return the persisted entity
     */
    EtagereDTO save(EtagereDTO etagereDTO);

    /**
     * Get all the etageres.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EtagereDTO> findAll(Pageable pageable);


    /**
     * Get the "id" etagere.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EtagereDTO> findOne(Long id);

    /**
     * Delete the "id" etagere.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
