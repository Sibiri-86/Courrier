package bf.gov.courrier.service.impl;

import bf.gov.courrier.service.EtagereService;
import bf.gov.courrier.domain.Etagere;
import bf.gov.courrier.repository.EtagereRepository;
import bf.gov.courrier.service.dto.EtagereDTO;
import bf.gov.courrier.service.mapper.EtagereMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Etagere.
 */
@Service
@Transactional
public class EtagereServiceImpl implements EtagereService {

    private final Logger log = LoggerFactory.getLogger(EtagereServiceImpl.class);

    private final EtagereRepository etagereRepository;

    private final EtagereMapper etagereMapper;

    public EtagereServiceImpl(EtagereRepository etagereRepository, EtagereMapper etagereMapper) {
        this.etagereRepository = etagereRepository;
        this.etagereMapper = etagereMapper;
    }

    /**
     * Save a etagere.
     *
     * @param etagereDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EtagereDTO save(EtagereDTO etagereDTO) {
        log.debug("Request to save Etagere : {}", etagereDTO);
        Etagere etagere = etagereMapper.toEntity(etagereDTO);
        etagere = etagereRepository.save(etagere);
        return etagereMapper.toDto(etagere);
    }

    /**
     * Get all the etageres.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EtagereDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Etageres");
        return etagereRepository.findAll(pageable)
            .map(etagereMapper::toDto);
    }


    /**
     * Get one etagere by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EtagereDTO> findOne(Long id) {
        log.debug("Request to get Etagere : {}", id);
        return etagereRepository.findById(id)
            .map(etagereMapper::toDto);
    }

    /**
     * Delete the etagere by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Etagere : {}", id);
        etagereRepository.deleteById(id);
    }
}
