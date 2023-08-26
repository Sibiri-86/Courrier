package bf.gov.courrier.service;

import bf.gov.courrier.service.dto.ColisDTO;
import bf.gov.courrier.service.dto.DroitProfileDTO;
import bf.gov.courrier.service.dto.EmballageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Emballage.
 */
public interface EmballageService {

    /**
     * Save a emballageDTO.
     *
     * @param emballageDTO the entity to save
     * @return the persisted entity
     */
    EmballageDTO save(EmballageDTO emballageDTO);

    /**
     * Get all the emballageDTO.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EmballageDTO> findAll(Pageable pageable);
    void retireEmballageColis(Long colisId);

    /**
     * Get the "id" emballageDTO.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EmballageDTO> findOne(Long id);

    Page<ColisDTO> findColisByEmballage(Pageable pageable,Long emballageId);
    
    

    /**
     * Delete the "id" emballageDTO.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
