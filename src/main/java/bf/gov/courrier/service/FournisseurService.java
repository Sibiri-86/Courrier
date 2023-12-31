package bf.gov.courrier.service;

import bf.gov.courrier.service.dto.FournisseurDTO;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Fournisseur.
 */
public interface FournisseurService {

    /**
     * Save a fournisseur.
     *
     * @param fournisseurDTO the entity to save
     * @return the persisted entity
     */
    FournisseurDTO save(FournisseurDTO fournisseurDTO);

    /**
     * Get all the fournisseurs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FournisseurDTO> findAll(Pageable pageable);


    /**
     * Get the "id" fournisseur.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FournisseurDTO> findOne(Long id);

    /**
     * Delete the "id" fournisseur.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    List<FournisseurDTO> findAllByPays(Long paysId);
}
