package bf.gov.courrier.service.impl;

import bf.gov.courrier.service.ReceptionService;
import bf.gov.courrier.domain.Agent;
import bf.gov.courrier.domain.Reception;
import bf.gov.courrier.repository.AgentRepository;
import bf.gov.courrier.repository.ReceptionRepository;
import bf.gov.courrier.service.dto.ReceptionDTO;
import bf.gov.courrier.service.mapper.AgentMapper;
import bf.gov.courrier.service.mapper.ReceptionMapper;

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
 * Service Implementation for managing Agent.
 */
@Service
@Transactional
public class ReceptionServiceImpl implements ReceptionService {

    private final Logger log = LoggerFactory.getLogger(ReceptionServiceImpl.class);

    private final ReceptionRepository receptionRepository;

    private final ReceptionMapper receptionMapper;

    public ReceptionServiceImpl(ReceptionRepository receptionRepository, ReceptionMapper receptionMapper) {
        this.receptionRepository = receptionRepository;
        this.receptionMapper = receptionMapper;
    }

    /**
     * Save a agent.
     *
     * @param receptionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ReceptionDTO save(ReceptionDTO receptionDTO) {
        log.debug("Request to save Agent : {}", receptionDTO);
        Reception reception = receptionMapper.toEntity(receptionDTO);
        
        reception = receptionRepository.save(reception);
        return receptionMapper.toDto(reception);
    }

    /**
     * Get all the receptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ReceptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all receptions");
        return receptionRepository.findAllByDeletedFalse(pageable)
            .map(receptionMapper::toDto);
    }


    /**
     * Get one reception by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReceptionDTO> findOne(Long id) {
        log.debug("Request to get reception : {}", id);
        return receptionRepository.findById(id)
            .map(receptionMapper::toDto);
    }

    /**
     * Delete the reception by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete reception : {}", id);
        receptionRepository.deleteById(id);
    }
    
    
}
