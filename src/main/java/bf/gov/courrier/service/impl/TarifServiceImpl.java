package bf.gov.courrier.service.impl;

import bf.gov.courrier.service.TarifService;
import bf.gov.courrier.domain.Tarif;
import bf.gov.courrier.repository.TarifRepository;
import bf.gov.courrier.service.dto.TarifDTO;
import bf.gov.courrier.service.mapper.TarifMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Tarif.
 */
@Service
@Transactional
public class TarifServiceImpl implements TarifService {

    private final Logger log = LoggerFactory.getLogger(TarifServiceImpl.class);

    private final TarifRepository tarifRepository;

    private final TarifMapper tarifMapper;

    public TarifServiceImpl(TarifRepository tarifRepository, TarifMapper tarifMapper) {
        this.tarifRepository = tarifRepository;
        this.tarifMapper = tarifMapper;
    }

    /**
     * Save a tarif.
     *
     * @param tarifDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TarifDTO save(TarifDTO tarifDTO) {
        log.debug("Request to save Tarif : {}", tarifDTO);
        Tarif tarif = tarifMapper.toEntity(tarifDTO);
         tarif.setNumero(tarifRepository.findAll().size()+0L );
        tarif = tarifRepository.save(tarif);
        return tarifMapper.toDto(tarif);
    }

    /**
     * Get all the tarifs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TarifDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tarifs");
        return tarifRepository.findAll(pageable)
            .map(tarifMapper::toDto);
    }


    /**
     * Get one tarif by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TarifDTO> findOne(Long id) {
        log.debug("Request to get Tarif : {}", id);
        return tarifRepository.findById(id)
            .map(tarifMapper::toDto);
    }

    /**
     * Delete the tarif by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tarif : {}", id);
        tarifRepository.deleteById(id);
    }
}
