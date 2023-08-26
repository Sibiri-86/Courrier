package bf.gov.courrier.service.impl;

import bf.gov.courrier.service.ReceptionService;
import bf.gov.courrier.domain.Agent;
import bf.gov.courrier.domain.Colis;
import bf.gov.courrier.domain.Reception;
import bf.gov.courrier.domain.Statut;
import bf.gov.courrier.repository.AgentRepository;
import bf.gov.courrier.repository.ClientRepository;
import bf.gov.courrier.repository.ColisRepository;
import bf.gov.courrier.repository.ReceptionRepository;
import bf.gov.courrier.service.dto.ColisDTO;
import bf.gov.courrier.service.dto.ReceptionDTO;
import bf.gov.courrier.service.mapper.AgentMapper;
import bf.gov.courrier.service.mapper.ColisMapper;
import bf.gov.courrier.service.mapper.ReceptionMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.j2objc.annotations.AutoreleasePool;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Service Implementation for managing Agent.
 */
@Service
@Transactional
public class ReceptionServiceImpl implements ReceptionService {

    private final Logger log = LoggerFactory.getLogger(ReceptionServiceImpl.class);

    private final ReceptionRepository receptionRepository;

    private final ReceptionMapper receptionMapper;
    @Autowired
    ColisRepository colisRepository;
    @Autowired 
    ColisMapper colisMapper;
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ReportGeneratorService generatorService;
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
    @Transactional
    public ReceptionDTO save(ReceptionDTO receptionDTO) {
        log.debug("Request to save Agent : {}", receptionDTO.getClient());
        
        if(receptionDTO.getId() == null) {
            receptionDTO.setDateReception(LocalDate.now());
        }
        Reception reception = receptionMapper.toEntity(receptionDTO);
        String numeroBordereau="";
        if(reception.getNumBordereau() == null) {
                  numeroBordereau = "BORD-"+LocalDate.now().getYear()+generateMois(String.valueOf(LocalDate.now().getMonthValue()))+"-"+generateNumero(getNumeroReception());
                  reception.setNumBordereau(numeroBordereau);

        }
        
        if(reception.getNumRecep() == null) {
            String numeroRecept = "RECEPT-"+LocalDate.now().getYear()+generateMois(String.valueOf(LocalDate.now().getMonthValue()))+"-"+generateNumero(getNumeroReception());
            reception.setNumRecep(numeroRecept);

        }
         
        reception = receptionRepository.save(reception);
        if(receptionDTO.getColis() !=null && !receptionDTO.getColis().isEmpty()) {
            int j = 1;
            for(ColisDTO col: receptionDTO.getColis()) {
                if( col.getStatut() == Statut.RECEPTIONNE) {
                    if(col.getId() == null) {
                        col.setDateReception(LocalDate.now());
                    }
                    col.setUserReceptionId(receptionDTO.getUserId());
                    Colis colis= colisMapper.toEntity(col);
                    colis.setReception(reception);
                    colis.setUserReceptionId(reception.getUserId());
                    colis.setStatut(Statut.RECEPTIONNE);
                   // colis.setNumBalle(reception.getNumBordereau());
                   if(colis.getNumColis() == null) {
                       colis.setNumColis(String.valueOf(colisRepository.findAll().size() + j).concat("-").concat(LocalDate.now().getYear()+generateMois(String.valueOf(LocalDate.now().getMonthValue()))) );

                   }

                    colisRepository.save(colis);
                } 
                 
            }
        }
        return receptionMapper.toDto(reception);
    }

    private synchronized String getNumeroReception(){
        int taille = receptionRepository.findAll().size() + 1;
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
    @Override
    public void deleteColis(Long colisId) {
        Optional<Colis> colis= colisRepository.findById(colisId);
        if(colis.isPresent()) {
            colis.get().setDeleted(Boolean.TRUE);
            colisMapper.toDto(colis.get());
        }
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
    
     public Page<ReceptionDTO> findAllByClient(Pageable pageable, Long clientId, LocalDate dateDebut, LocalDate dateFin) {
        return receptionRepository.findAllByDeletedFalseAndClientIdAndDateReceptionBetween(pageable, clientId, dateDebut.plusDays(-1L), dateFin.plusDays(1L))
            .map(receptionMapper::toDto);
    }

     @Override
     public Page<ReceptionDTO> findAllByFournisseur(Pageable pageable, Long fournisseurId, LocalDate dateDebut, LocalDate dateFin) {
        return receptionRepository.findAllByDeletedFalseAndFournisseurIdAndDateReceptionBetween(pageable, fournisseurId, dateDebut.plusDays(-1L), dateFin.plusDays(1L))
            .map(receptionMapper::toDto);
    }
     
      @Override
     public Page<ReceptionDTO> findAllByClientAndFournisseur(Pageable pageable, Long clientId, Long fournisseurId, LocalDate dateDebut, LocalDate dateFin) {
        return receptionRepository.findAllByDeletedFalseAndClientIdAndFournisseurIdAndDateReceptionBetween(pageable,clientId, fournisseurId, dateDebut.plusDays(-1L), dateFin.plusDays(1L))
            .map(receptionMapper::toDto);
    }
     
     
     @Override
     public byte[] generateReport(Long  receptId) throws IOException, JRException {
         HashMap<String, ? super Object> parameterMap = new HashMap<>();
         Reception reception = receptionRepository.getOne(receptId);
                  parameterMap.put("nom", reception.getClient().getNom());
                  parameterMap.put("prenom", reception.getClient().getPrenom());
                  parameterMap.put("numBordereau", reception.getNumBordereau());
                  parameterMap.put("numRecep", reception.getNumRecep());
                  parameterMap.put("users", String.valueOf(reception.getUserId()));
                  parameterMap.put("fournisseur", reception.getFournisseur().getNom());
                  //parameterMap.put("dateSoins", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                 List<Colis> colis = colisRepository.findAllByReceptionIdAndDeletedFalse(receptId);
                 return buildReport( colis, parameterMap,false);
             
         
     }
     
     
     
     private byte[] buildReport(
           final Object dto,
            final HashMap<String, ? super Object> parameterMap, Boolean source) throws IOException, JRException {


        
         InputStream fileInputStream = getClass().getClassLoader().getResourceAsStream("reports/reception.jrxml");
        //convert DTO into the JsonDatasource
        InputStream jsonFile = this.convertDtoToInputStream(dto);
        System.out.println("le jsonFile"+jsonFile);
        JRDataSource jsonDataSource = new JsonDataSource(jsonFile);
         System.out.println("le fileInputStream"+fileInputStream);
             byte[] reportFile = generatorService.genererRapport(fileInputStream, parameterMap, jsonDataSource,source);
        return reportFile;
    }
     
     private InputStream convertDtoToInputStream(final Object dto) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // Java object to JSON string
        String jsonString = mapper.writeValueAsString(dto);
        log.info("Json String: " + jsonString);
        //use ByteArrayInputStream to get the bytes of the String and convert them to InputStream.
        InputStream inputStream = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
        return inputStream;
    }
     
     @Override
     public Page<ReceptionDTO> findAllByPeriode(Pageable pageable, LocalDate dateDebut, LocalDate dateFin) {
        return receptionRepository.findAllByDateReceptionBetweenAndDeletedFalse(pageable, dateDebut.plusDays(-1L), dateFin.plusDays(1L))
            .map(receptionMapper::toDto);
    }

    
     public Page<ColisDTO> findColisByReception(Pageable pageable, Long receptionId) {
        return colisRepository.findAllByReceptionIdAndDeletedFalse(receptionId, pageable)
            .map(colisMapper::toDto);
    }
     
     public Page<ColisDTO> findAllColis(Pageable pageable) {
        return colisRepository.findAllByDeletedFalseAndStatut( pageable, Statut.RECEPTIONNE)
            .map(colisMapper::toDto);
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
