package bf.gov.courrier.service;

import bf.gov.courrier.service.dto.AgentDTO;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Agent.
 */
public interface AgentService {

    /**
     * Save a agent.
     *
     * @param agentDTO the entity to save
     * @return the persisted entity
     */
    AgentDTO save(AgentDTO agentDTO);

    /**
     * Get all the agents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AgentDTO> findAll(Pageable pageable);


    /**
     * Get the "id" agent.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AgentDTO> findOne(Long id);

    /**
     * Delete the "id" agent.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    List<AgentDTO> findAllBySite(Long siteId);
}
