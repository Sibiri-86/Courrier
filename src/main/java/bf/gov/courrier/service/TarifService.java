package bf.gov.courrier.service;

import bf.gov.courrier.service.dto.TarifDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Tarif.
 */
public interface TarifService {

    /**
     * Save a tarif.
     *
     * @param tarifDTO the entity to save
     * @return the persisted entity
     */
    TarifDTO save(TarifDTO tarifDTO);

    /**
     * Get all the tarifs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TarifDTO> findAll(Pageable pageable);


    /**
     * Get the "id" tarif.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TarifDTO> findOne(Long id);

    /**
     * Delete the "id" tarif.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
