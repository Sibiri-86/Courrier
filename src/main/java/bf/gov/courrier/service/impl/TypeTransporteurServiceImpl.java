package bf.gov.courrier.service.impl;

import bf.gov.courrier.service.TypeTransporteurService;
import bf.gov.courrier.domain.TypeTransporteur;
import bf.gov.courrier.repository.TypeTransporteurRepository;
import bf.gov.courrier.service.dto.TypeTransporteurDTO;
import bf.gov.courrier.service.mapper.TypeTransporteurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing TypeTransporteur.
 */
@Service
@Transactional
public class TypeTransporteurServiceImpl implements TypeTransporteurService {

    private final Logger log = LoggerFactory.getLogger(TypeTransporteurServiceImpl.class);

    private final TypeTransporteurRepository typeTransporteurRepository;

    private final TypeTransporteurMapper typeTransporteurMapper;

    public TypeTransporteurServiceImpl(TypeTransporteurRepository typeTransporteurRepository, TypeTransporteurMapper typeTransporteurMapper) {
        this.typeTransporteurRepository = typeTransporteurRepository;
        this.typeTransporteurMapper = typeTransporteurMapper;
    }

    /**
     * Save a typeTransporteur.
     *
     * @param typeTransporteurDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TypeTransporteurDTO save(TypeTransporteurDTO typeTransporteurDTO) {
        log.debug("Request to save TypeTransporteur : {}", typeTransporteurDTO);
        TypeTransporteur typeTransporteur = typeTransporteurMapper.toEntity(typeTransporteurDTO);
         typeTransporteur.setNumero(typeTransporteurRepository.findAll().size()+0L );
        typeTransporteur = typeTransporteurRepository.save(typeTransporteur);
        return typeTransporteurMapper.toDto(typeTransporteur);
    }

    /**
     * Get all the typeTransporteurs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TypeTransporteurDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypeTransporteurs");
        return typeTransporteurRepository.findAll(pageable)
            .map(typeTransporteurMapper::toDto);
    }


    /**
     * Get one typeTransporteur by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TypeTransporteurDTO> findOne(Long id) {
        log.debug("Request to get TypeTransporteur : {}", id);
        return typeTransporteurRepository.findById(id)
            .map(typeTransporteurMapper::toDto);
    }

    /**
     * Delete the typeTransporteur by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeTransporteur : {}", id);
        typeTransporteurRepository.deleteById(id);
    }
}
