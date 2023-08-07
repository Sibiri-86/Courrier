package bf.gov.courrier.service.impl;

import bf.gov.courrier.service.PaysService;
import bf.gov.courrier.domain.Pays;
import bf.gov.courrier.repository.PaysRepository;
import bf.gov.courrier.service.dto.PaysDTO;
import bf.gov.courrier.service.mapper.PaysMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Pays.
 */
@Service
@Transactional
public class PaysServiceImpl implements PaysService {

    private final Logger log = LoggerFactory.getLogger(PaysServiceImpl.class);

    private final PaysRepository paysRepository;

    private final PaysMapper paysMapper;

    public PaysServiceImpl(PaysRepository paysRepository, PaysMapper paysMapper) {
        this.paysRepository = paysRepository;
        this.paysMapper = paysMapper;
    }

    /**
     * Save a pays.
     *
     * @param paysDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PaysDTO save(PaysDTO paysDTO) {
        log.debug("Request to save Pays : {}", paysDTO);
        Pays pays = paysMapper.toEntity(paysDTO);
         pays.setNumero(paysRepository.findAll().size()+0L );
        pays = paysRepository.save(pays);
        return paysMapper.toDto(pays);
    }

    /**
     * Get all the pays.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PaysDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pays");
        return paysRepository.findAll(pageable)
            .map(paysMapper::toDto);
    }


    /**
     * Get one pays by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PaysDTO> findOne(Long id) {
        log.debug("Request to get Pays : {}", id);
        return paysRepository.findById(id)
            .map(paysMapper::toDto);
    }

    /**
     * Delete the pays by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Pays : {}", id);
        paysRepository.deleteById(id);
    }
}
