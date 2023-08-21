package bf.gov.courrier.service.impl;

import bf.gov.courrier.service.TailleBalleService;
import bf.gov.courrier.domain.TailleBalle;
import bf.gov.courrier.repository.TailleBalleRepository;
import bf.gov.courrier.service.dto.TailleBalleDTO;
import bf.gov.courrier.service.mapper.TailleBalleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing TailleBalle.
 */
@Service
@Transactional
public class TailleBalleServiceImpl implements TailleBalleService {

    private final Logger log = LoggerFactory.getLogger(TailleBalleServiceImpl.class);

    private final TailleBalleRepository tailleBalleRepository;

    private final TailleBalleMapper tailleBalleMapper;

    public TailleBalleServiceImpl(TailleBalleRepository tailleBalleRepository, TailleBalleMapper tailleBalleMapper) {
        this.tailleBalleRepository = tailleBalleRepository;
        this.tailleBalleMapper = tailleBalleMapper;
    }

    /**
     * Save a tailleBalle.
     *
     * @param tailleBalleDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TailleBalleDTO save(TailleBalleDTO tailleBalleDTO) {
        log.debug("Request to save TailleBalle : {}", tailleBalleDTO);
        TailleBalle tailleBalle = tailleBalleMapper.toEntity(tailleBalleDTO);
        
        tailleBalle = tailleBalleRepository.save(tailleBalle);
        return tailleBalleMapper.toDto(tailleBalle);
    }

    /**
     * Get all the tailleBalles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TailleBalleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TailleBalles");
        return tailleBalleRepository.findAllByDeletedFalse(pageable)
            .map(tailleBalleMapper::toDto);
    }


    /**
     * Get one tailleBalle by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TailleBalleDTO> findOne(Long id) {
        log.debug("Request to get TailleBalle : {}", id);
        return tailleBalleRepository.findById(id)
            .map(tailleBalleMapper::toDto);
    }

    /**
     * Delete the tailleBalle by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TailleBalle : {}", id);
        tailleBalleRepository.deleteById(id);
    }
}
