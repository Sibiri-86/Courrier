package bf.gov.courrier.service;

import bf.gov.courrier.service.dto.SiteDTO;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Site.
 */
public interface SiteService {

    /**
     * Save a site.
     *
     * @param siteDTO the entity to save
     * @return the persisted entity
     */
    SiteDTO save(SiteDTO siteDTO);

    /**
     * Get all the sites.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SiteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" site.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SiteDTO> findOne(Long id);

    /**
     * Delete the "id" site.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    List<SiteDTO> findAllByPays(Long paysId);
}
