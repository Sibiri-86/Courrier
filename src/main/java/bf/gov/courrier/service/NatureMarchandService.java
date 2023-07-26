package bf.gov.courrier.service;

import bf.gov.courrier.service.dto.NatureMarchandDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing NatureMarchand.
 */
public interface NatureMarchandService {

    /**
     * Save a natureMarchand.
     *
     * @param natureMarchandDTO the entity to save
     * @return the persisted entity
     */
    NatureMarchandDTO save(NatureMarchandDTO natureMarchandDTO);

    /**
     * Get all the natureMarchands.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NatureMarchandDTO> findAll(Pageable pageable);


    /**
     * Get the "id" natureMarchand.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NatureMarchandDTO> findOne(Long id);

    /**
     * Delete the "id" natureMarchand.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
