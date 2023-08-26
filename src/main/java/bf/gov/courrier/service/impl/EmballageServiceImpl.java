package bf.gov.courrier.service.impl;

import bf.gov.courrier.domain.Colis;
import bf.gov.courrier.domain.Emballage;
import bf.gov.courrier.domain.Statut;
import bf.gov.courrier.repository.ColisRepository;
import bf.gov.courrier.repository.EmballageRepository;
import bf.gov.courrier.service.EmballageService;
import bf.gov.courrier.service.dto.ColisDTO;
import bf.gov.courrier.service.dto.EmballageDTO;
import bf.gov.courrier.service.mapper.ColisMapper;
import bf.gov.courrier.service.mapper.EmballageMapper;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Service Implementation for managing Emballage.
 */
@Service
@Transactional
public class EmballageServiceImpl  implements EmballageService{

    private final Logger log = LoggerFactory.getLogger(EmballageServiceImpl.class);

    private final EmballageRepository emballageRepository;

    private final EmballageMapper emballageMapper;
    
     @Autowired
    ColisRepository colisRepository;
    @Autowired 
    ColisMapper colisMapper;

    public EmballageServiceImpl(EmballageRepository emballageRepository, EmballageMapper emballageMapper) {
        this.emballageRepository = emballageRepository;
        this.emballageMapper = emballageMapper;
    }


    /**
     * Save a emballage.
     *
     * @param emballageDTO the entity to save
     * @return the persisted entity
     */
    @Override
    @Transactional
    public EmballageDTO save(EmballageDTO emballageDTO) {
        log.debug("Request to save Emballage : {}", emballageDTO);
        Emballage emballage = emballageMapper.toEntity(emballageDTO);
          String numeroBal = "BAL-"+LocalDate.now().getYear()+generateMois(String.valueOf(LocalDate.now().getMonthValue()))+"-"+generateNumero(getNumeroBall());
           
          if(emballage.getNumBalle() == null) {
              emballage.setNumBalle(numeroBal);
          }
          

       // emballage.setNumBalle(String.valueOf(emballageDTO.getColis().size()).concat("-").concat(LocalDate.now().toString()).concat(" /"+emballageRepository.findAll().size() ));
        emballage = emballageRepository.save(emballage);
        
            if(emballageDTO.getColis() !=null && !emballageDTO.getColis().isEmpty()) {
               
            for(ColisDTO col: emballageDTO.getColis()) {
               col.setUserEmballeId(emballageDTO.getUserId());
                Colis colis= colisMapper.toEntity(col);
                 colis.setEmballage(emballage);
                 colis.setStatut(Statut.EMBALLE);
                 if(colis.getNumBalle() == null) {
                     colis.setNumBalle(numeroBal);
                 }
                  colis.setDateEmballe(LocalDate.now());
                 colisRepository.save(colis);
            }
             
        }
        
        return emballageMapper.toDto(emballage);
    }
    
     private synchronized String getNumeroBall(){
        int taille = emballageRepository.findAll().size() + 1;
        return String.valueOf(taille);
    }
    
    private static String generateNumero ( String keyList ) {
                        int taille = keyList.length();
                        if(taille==1)
                            return "00000"+keyList;
                        if(taille==2)
                            return "0000"+keyList;
                        if(taille==3)
                            return "000"+keyList;
                        if(taille==4)
                            return "00"+keyList;
                        if(taille==5)
                            return "0"+keyList;
                        if(taille==6)
                            return keyList;
                   return "000000";
	  }
     
      private static String generateMois ( String keyList ) {
                        int taille = keyList.length();
                        if(taille==1)
                            return "0"+keyList;
                        if(taille==2)
                            return keyList;
                        
                   return "000";
	}

    /**
     * Get all the emballage.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EmballageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Emballages");
        return emballageRepository.findAllByDeletedFalse(pageable)
            .map(emballageMapper::toDto);
    }

    
    

    /**
     * Get one emballage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EmballageDTO> findOne(Long id) {
        log.debug("Request to get Etagere : {}", id);
        return emballageRepository.findById(id)
            .map(emballageMapper::toDto);
    }
    
    @Override
    public Page<ColisDTO> findColisByEmballage(Pageable pageable, Long emballageId) {
        return colisRepository.findAllByEmballageIdAndDeletedFalseAndStatut(emballageId, pageable, Statut.EMBALLE)
            .map(colisMapper::toDto);
    }

    
    @Override
    public void retireEmballageColis(Long colisId) {
        Optional<Colis> coli = colisRepository.findById(colisId);
        if(coli.isPresent()) {
            coli.get().setEmballage(null);
            coli.get().setStatut(Statut.RECEPTIONNE);
            colisRepository.save(coli.get());
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
