package bf.gov.courrier.service.impl;

import bf.gov.courrier.service.AgentService;
import bf.gov.courrier.domain.Agent;
import bf.gov.courrier.domain.DroitProfile;
import bf.gov.courrier.repository.AgentRepository;
import bf.gov.courrier.repository.DroitProfileRepository;
import bf.gov.courrier.service.DroitProfileService;
import bf.gov.courrier.service.dto.AgentDTO;
import bf.gov.courrier.service.dto.DroitProfileDTO;
import bf.gov.courrier.service.mapper.AgentMapper;
import bf.gov.courrier.service.mapper.DroitProfileMapper;
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
public class DroitProfileServiceImpl implements DroitProfileService {

    private final Logger log = LoggerFactory.getLogger(DroitProfileServiceImpl.class);

    private final DroitProfileRepository droitProfileRepository;

    private final DroitProfileMapper droitProfileMapper;

    public DroitProfileServiceImpl(DroitProfileRepository droitProfileRepository, DroitProfileMapper droitProfileMapper) {
        this.droitProfileRepository = droitProfileRepository;
        this.droitProfileMapper = droitProfileMapper;
    }

    /**
     * Save a agent.
     *
     * @param agentDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DroitProfileDTO save(DroitProfileDTO droitProfileDTO) {
        log.debug("Request to save Agent : {}", droitProfileDTO);
        DroitProfile droitProfile = droitProfileMapper.toEntity(droitProfileDTO);
        
        droitProfile = droitProfileRepository.save(droitProfile);
        return droitProfileMapper.toDto(droitProfile);
    }

    /**
     * Get all the agents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DroitProfileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Agents");
        return droitProfileRepository.findAllByDeletedFalse(pageable)
            .map(droitProfileMapper::toDto);
    }


    /**
     * Get one agent by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DroitProfileDTO> findOne(Long id) {
        log.debug("Request to get Agent : {}", id);
        return droitProfileRepository.findById(id)
            .map(droitProfileMapper::toDto);
    }

    /**
     * Delete the agent by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Agent : {}", id);
        droitProfileRepository.deleteById(id);
    }
    
     public List<DroitProfileDTO> findAllByProfile(Long profileId) {
          return droitProfileRepository.findByProfileIdAndDeletedFalse(profileId).stream()
            .map(droitProfileMapper::toDto).collect(Collectors.toList());
     }
}
