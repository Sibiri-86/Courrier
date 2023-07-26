package bf.gov.courrier.service.impl;

import bf.gov.courrier.service.TransitaireService;
import bf.gov.courrier.domain.Transitaire;
import bf.gov.courrier.repository.TransitaireRepository;
import bf.gov.courrier.service.dto.TransitaireDTO;
import bf.gov.courrier.service.mapper.TransitaireMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Transitaire.
 */
@Service
@Transactional
public class TransitaireServiceImpl implements TransitaireService {

    private final Logger log = LoggerFactory.getLogger(TransitaireServiceImpl.class);

    private final TransitaireRepository transitaireRepository;

    private final TransitaireMapper transitaireMapper;

    public TransitaireServiceImpl(TransitaireRepository transitaireRepository, TransitaireMapper transitaireMapper) {
        this.transitaireRepository = transitaireRepository;
        this.transitaireMapper = transitaireMapper;
    }

    /**
     * Save a transitaire.
     *
     * @param transitaireDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TransitaireDTO save(TransitaireDTO transitaireDTO) {
        log.debug("Request to save Transitaire : {}", transitaireDTO);
        Transitaire transitaire = transitaireMapper.toEntity(transitaireDTO);
        transitaire = transitaireRepository.save(transitaire);
        return transitaireMapper.toDto(transitaire);
    }

    /**
     * Get all the transitaires.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TransitaireDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Transitaires");
        return transitaireRepository.findAll(pageable)
            .map(transitaireMapper::toDto);
    }


    /**
     * Get one transitaire by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TransitaireDTO> findOne(Long id) {
        log.debug("Request to get Transitaire : {}", id);
        return transitaireRepository.findById(id)
            .map(transitaireMapper::toDto);
    }

    /**
     * Delete the transitaire by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Transitaire : {}", id);
        transitaireRepository.deleteById(id);
    }
}
