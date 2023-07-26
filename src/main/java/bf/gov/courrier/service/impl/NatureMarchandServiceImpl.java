package bf.gov.courrier.service.impl;

import bf.gov.courrier.service.NatureMarchandService;
import bf.gov.courrier.domain.NatureMarchand;
import bf.gov.courrier.repository.NatureMarchandRepository;
import bf.gov.courrier.service.dto.NatureMarchandDTO;
import bf.gov.courrier.service.mapper.NatureMarchandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing NatureMarchand.
 */
@Service
@Transactional
public class NatureMarchandServiceImpl implements NatureMarchandService {

    private final Logger log = LoggerFactory.getLogger(NatureMarchandServiceImpl.class);

    private final NatureMarchandRepository natureMarchandRepository;

    private final NatureMarchandMapper natureMarchandMapper;

    public NatureMarchandServiceImpl(NatureMarchandRepository natureMarchandRepository, NatureMarchandMapper natureMarchandMapper) {
        this.natureMarchandRepository = natureMarchandRepository;
        this.natureMarchandMapper = natureMarchandMapper;
    }

    /**
     * Save a natureMarchand.
     *
     * @param natureMarchandDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NatureMarchandDTO save(NatureMarchandDTO natureMarchandDTO) {
        log.debug("Request to save NatureMarchand : {}", natureMarchandDTO);
        NatureMarchand natureMarchand = natureMarchandMapper.toEntity(natureMarchandDTO);
        natureMarchand = natureMarchandRepository.save(natureMarchand);
        return natureMarchandMapper.toDto(natureMarchand);
    }

    /**
     * Get all the natureMarchands.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NatureMarchandDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NatureMarchands");
        return natureMarchandRepository.findAll(pageable)
            .map(natureMarchandMapper::toDto);
    }


    /**
     * Get one natureMarchand by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NatureMarchandDTO> findOne(Long id) {
        log.debug("Request to get NatureMarchand : {}", id);
        return natureMarchandRepository.findById(id)
            .map(natureMarchandMapper::toDto);
    }

    /**
     * Delete the natureMarchand by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NatureMarchand : {}", id);
        natureMarchandRepository.deleteById(id);
    }
}
