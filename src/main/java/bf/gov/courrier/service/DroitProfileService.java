package bf.gov.courrier.service;

import bf.gov.courrier.service.dto.DroitProfileDTO;
import bf.gov.courrier.service.dto.MenuDTO;
import bf.gov.courrier.service.dto.ProfileDTO;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TailleBalle.
 */
public interface DroitProfileService {

    /**
     * Save a tailleBalle.
     *
     * @param droitProfileDTO the entity to save
     * @return the persisted entity
     */
    DroitProfileDTO save(DroitProfileDTO droitProfileDTO);

    /**
     * Get all the tailleBalles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DroitProfileDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tailleBalle.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DroitProfileDTO> findOne(Long id);

    /**
     * Delete the "id" tailleBalle.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    List<DroitProfileDTO> findAllByProfile(Long profileId);
}
