package bf.gov.courrier.service.impl;

import bf.gov.courrier.domain.Chargement;
import bf.gov.courrier.domain.Colis;
import bf.gov.courrier.domain.Emballage;
import bf.gov.courrier.domain.Statut;
import bf.gov.courrier.repository.ChargementRepository;
import bf.gov.courrier.repository.ColisRepository;
import bf.gov.courrier.repository.EmballageRepository;
import bf.gov.courrier.service.ChargementService;
import bf.gov.courrier.service.dto.ChargementDTO;
import bf.gov.courrier.service.dto.ColisDTO;
import bf.gov.courrier.service.dto.EmballageDTO;
import bf.gov.courrier.service.mapper.ChargementMapper;
import bf.gov.courrier.service.mapper.EmballageMapper;
import bf.gov.courrier.service.mapper.UserMapper;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Service Implementation for managing Emballage.
 */
@Service
@Transactional
public class ChargementServiceImpl  implements ChargementService{

    private final Logger log = LoggerFactory.getLogger(ChargementServiceImpl.class);

    private final ChargementRepository chargementRepository;

    private final ChargementMapper chargementMapper;
    
    @Autowired
    EmballageRepository emballageRepository;
    @Autowired 
    EmballageMapper emballageMapper;
    @Autowired
    ColisRepository colisRepository;
   

    public ChargementServiceImpl(ChargementRepository chargementRepository, ChargementMapper chargementMapper) {
        this.chargementRepository = chargementRepository;
        this.chargementMapper = chargementMapper;
    }


    /**
     * Save a emballage.
     *
     * @param chargementDTO the entity to save
     * @return the persisted entity
     */
    @Override
    @Transactional
    public ChargementDTO save(ChargementDTO chargementDTO) {
        log.debug("Request to save Emballage : {}", chargementDTO);
        Chargement  chargement  = chargementMapper.toEntity(chargementDTO);
        int i =chargementRepository.findAll().size() + 1;
        String groupe = LocalDate.now().toString().concat(String.valueOf(i));
        chargement.setNumGroupage(groupe);
        chargement.setNumBalle(String.valueOf(chargementDTO.getEmballages().size()).concat("-").concat(LocalDate.now().toString()).concat(" /"+chargementRepository.findAll().size() ));

        chargement = chargementRepository.save(chargement);
        
            if(chargementDTO.getEmballages() !=null && !chargementDTO.getEmballages().isEmpty()) {
            for(EmballageDTO col: chargementDTO.getEmballages()) {
                Emballage colis= emballageMapper.toEntity(col);
                 colis.setChargement(chargement);
                 colisRepository.saveAll(colisRepository.findAllByEmballageIdAndDeletedFalseAndStatut(colis.getId(), Statut.EMBALLE)
                         .stream().peek(col1->{
                             col1.setStatut(Statut.CHARGEMENT);
                             col1.setDateChargement(LocalDate.now());
                             col1.setUserChargementId(chargementDTO.getUserId());
                         }).collect(Collectors.toList()));
                        
                         
                emballageRepository.save(colis);
            }
        }
        return chargementMapper.toDto(chargement);
    }

    /**
     * Get all the emballage.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ChargementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Emballages");
        return chargementRepository.findAllByDeletedFalse(pageable)
            .map(chargementMapper::toDto);
    }


    /**
     * Get one emballage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ChargementDTO> findOne(Long id) {
        log.debug("Request to get Etagere : {}", id);
        return chargementRepository.findById(id)
            .map(chargementMapper::toDto);
    }
    
    @Override
    public Page<EmballageDTO> findEmballage(Pageable pageable, Long chargementId) {
        return emballageRepository.findAllByChargementIdAndDeletedFalse(chargementId, pageable)
            .map(emballageMapper::toDto);
    }

    
    @Override
    public void retireEmballage(Long emballageId) {
        Optional<Emballage> coli = emballageRepository.findById(emballageId);
        if(coli.isPresent()) {
            coli.get().setChargement(null);
            emballageRepository.save(coli.get());
        }
        
    }
    /**
     * Delete the emballage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Emballage : {}", id);
        emballageRepository.deleteById(id);
    }
}
