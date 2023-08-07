package bf.gov.courrier.service.impl;

import bf.gov.courrier.service.SiteService;
import bf.gov.courrier.domain.Site;
import bf.gov.courrier.repository.SiteRepository;
import bf.gov.courrier.service.dto.SiteDTO;
import bf.gov.courrier.service.mapper.SiteMapper;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Site.
 */
@Service
@Transactional
public class SiteServiceImpl implements SiteService {

    private final Logger log = LoggerFactory.getLogger(SiteServiceImpl.class);

    private final SiteRepository siteRepository;

    private final SiteMapper siteMapper;

    public SiteServiceImpl(SiteRepository siteRepository, SiteMapper siteMapper) {
        this.siteRepository = siteRepository;
        this.siteMapper = siteMapper;
    }

    /**
     * Save a site.
     *
     * @param siteDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SiteDTO save(SiteDTO siteDTO) {
        log.debug("Request to save Site : {}", siteDTO);
        Site site = siteMapper.toEntity(siteDTO);
         site.setNumero(siteRepository.findAll().size()+0L );
        site = siteRepository.save(site);
        return siteMapper.toDto(site);
    }

    /**
     * Get all the sites.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SiteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Sites");
        return siteRepository.findAll(pageable)
            .map(siteMapper::toDto);
    }


    /**
     * Get one site by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SiteDTO> findOne(Long id) {
        log.debug("Request to get Site : {}", id);
        return siteRepository.findById(id)
            .map(siteMapper::toDto);
    }

    /**
     * Delete the site by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Site : {}", id);
        siteRepository.deleteById(id);
    }
    
    public List<SiteDTO> findAllByPays(Long siteId) {
          return siteRepository.findByPaysId(siteId).stream()
            .map(siteMapper::toDto).collect(Collectors.toList());
     }
}
