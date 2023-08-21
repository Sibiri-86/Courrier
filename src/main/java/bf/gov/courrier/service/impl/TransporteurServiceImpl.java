package bf.gov.courrier.service.impl;

import bf.gov.courrier.service.TransporteurService;
import bf.gov.courrier.domain.Transporteur;
import bf.gov.courrier.repository.TransporteurRepository;
import bf.gov.courrier.service.dto.TransporteurDTO;
import bf.gov.courrier.service.mapper.TransporteurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Transporteur.
 */
@Service
@Transactional
public class TransporteurServiceImpl implements TransporteurService {

    private final Logger log = LoggerFactory.getLogger(TransporteurServiceImpl.class);

    private final TransporteurRepository transporteurRepository;

    private final TransporteurMapper transporteurMapper;

    public TransporteurServiceImpl(TransporteurRepository transporteurRepository, TransporteurMapper transporteurMapper) {
        this.transporteurRepository = transporteurRepository;
        this.transporteurMapper = transporteurMapper;
    }

    /**
     * Save a transporteur.
     *
     * @param transporteurDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TransporteurDTO save(TransporteurDTO transporteurDTO) {
        log.debug("Request to save Transporteur : {}", transporteurDTO);
        Transporteur transporteur = transporteurMapper.toEntity(transporteurDTO);
        
        transporteur = transporteurRepository.save(transporteur);
        return transporteurMapper.toDto(transporteur);
    }

    /**
     * Get all the transporteurs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TransporteurDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Transporteurs");
        return transporteurRepository.findAllByDeletedFalse(pageable)
            .map(transporteurMapper::toDto);
    }


    /**
     * Get one transporteur by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TransporteurDTO> findOne(Long id) {
        log.debug("Request to get Transporteur : {}", id);
        return transporteurRepository.findById(id)
            .map(transporteurMapper::toDto);
    }

    /**
     * Delete the transporteur by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Transporteur : {}", id);
        transporteurRepository.deleteById(id);
    }
}
