package bf.gov.courrier.service;

import bf.gov.courrier.service.dto.TypeTransporteurDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TypeTransporteur.
 */
public interface TypeTransporteurService {

    /**
     * Save a typeTransporteur.
     *
     * @param typeTransporteurDTO the entity to save
     * @return the persisted entity
     */
    TypeTransporteurDTO save(TypeTransporteurDTO typeTransporteurDTO);

    /**
     * Get all the typeTransporteurs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TypeTransporteurDTO> findAll(Pageable pageable);


    /**
     * Get the "id" typeTransporteur.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TypeTransporteurDTO> findOne(Long id);

    /**
     * Delete the "id" typeTransporteur.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
