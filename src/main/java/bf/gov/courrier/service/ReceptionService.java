package bf.gov.courrier.service;

import bf.gov.courrier.service.dto.AgentDTO;
import bf.gov.courrier.service.dto.ColisDTO;
import bf.gov.courrier.service.dto.ReceptionDTO;
import java.time.LocalDate;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Agent.
 */
public interface ReceptionService {

    /**
     * Save a agent.
     *
     * @param receptionDTO the entity to save
     * @return the persisted entity
     */
    ReceptionDTO save(ReceptionDTO receptionDTO);

    /**
     * Get all the agents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ReceptionDTO> findAll(Pageable pageable);
    
    Page<ColisDTO> findColisByReception(Pageable pageable,Long receptionId);

    Page<ColisDTO> findAllColis(Pageable pageable);
    Page<ReceptionDTO> findAllByClient(Pageable pageable, Long clientId, LocalDate dateDebut, LocalDate dateFin);
    Page<ReceptionDTO> findAllByFournisseur(Pageable pageable, Long fournisseurId, LocalDate dateDebut, LocalDate dateFin);
    Page<ReceptionDTO> findAllByPeriode(Pageable pageable,  LocalDate dateDebut, LocalDate dateFin);
    Page<ReceptionDTO> findAllByClientAndFournisseur(Pageable pageable, Long clientId, Long fournisseurId, LocalDate dateDebut, LocalDate dateFin);


    /**
     * Get the "id" agent.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ReceptionDTO> findOne(Long id);

    /**
     * Delete the "id" agent.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    void deleteColis(Long colisId);
    
   // List<AgentDTO> findAllBySite(Long siteId);
}
