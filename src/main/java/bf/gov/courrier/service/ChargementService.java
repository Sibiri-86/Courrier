package bf.gov.courrier.service;

import bf.gov.courrier.service.dto.AgentDTO;
import bf.gov.courrier.service.dto.ChargementDTO;
import bf.gov.courrier.service.dto.ColisDTO;
import bf.gov.courrier.service.dto.EmballageDTO;
import bf.gov.courrier.service.dto.ReceptionDTO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Agent.
 */
public interface ChargementService {

    /**
     * Save a agent.
     *
     * @param chargementDTO the entity to save
     * @return the persisted entity
     */
    ChargementDTO save(ChargementDTO chargementDTO);

    /**
     * Get all the agents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ChargementDTO> findAll(Pageable pageable);
    
    Page<EmballageDTO> findEmballage(Pageable pageable,Long chargementId);



    /**
     * Get the "id" agent.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ChargementDTO> findOne(Long id);

    /**
     * Delete the "id" agent.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    void retireEmballage(Long emballageId);
    
   // List<AgentDTO> findAllBySite(Long siteId);
}
