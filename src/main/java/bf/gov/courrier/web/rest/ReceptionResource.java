package bf.gov.courrier.web.rest;
import bf.gov.courrier.service.ReceptionService;
import bf.gov.courrier.web.rest.errors.BadRequestAlertException;
import bf.gov.courrier.web.rest.util.HeaderUtil;
import bf.gov.courrier.web.rest.util.PaginationUtil;
import bf.gov.courrier.service.dto.AgentDTO;
import bf.gov.courrier.service.dto.ColisDTO;
import bf.gov.courrier.service.dto.ReceptionDTO;
import bf.gov.courrier.service.dto.SearchDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Agent.
 */
@RestController
@RequestMapping("/api")
public class ReceptionResource {

    private final Logger log = LoggerFactory.getLogger(ReceptionResource.class);

    private static final String ENTITY_NAME = "reception";

    private final ReceptionService receptionService;

    public ReceptionResource(ReceptionService receptionService) {
        this.receptionService = receptionService;
    }

    /**
     * POST  /receptions : Create a new reception.
     *
     * @param receptionDTO the receptionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new receptionDTO, or with status 400 (Bad Request) if the reception has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/receptions")
    public ResponseEntity<ReceptionDTO> createreception(@RequestBody ReceptionDTO receptionDTO) throws URISyntaxException {
        log.debug("REST request to save reception : {}", receptionDTO);
        if (receptionDTO.getId() != null) {
            throw new BadRequestAlertException("A new reception cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReceptionDTO result = receptionService.save(receptionDTO);
        return ResponseEntity.created(new URI("/api/receptions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /receptions : Updates an existing reception.
     *
     * @param receptionDTO the receptionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated receptionDTO,
     * or with status 400 (Bad Request) if the receptionDTO is not valid,
     * or with status 500 (Internal Server Error) if the receptionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/receptions")
    public ResponseEntity<ReceptionDTO> updatereception(@RequestBody ReceptionDTO receptionDTO) throws URISyntaxException {
        log.debug("REST request to update reception : {}", receptionDTO);
        if (receptionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReceptionDTO result = receptionService.save(receptionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, receptionDTO.getId().toString()))
            .body(result);
    }
    
    

    /**
     * GET  /receptions : get all the receptions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of receptions in body
     */
    @GetMapping("/receptions")
    public ResponseEntity<List<ReceptionDTO>> getAllreceptions(Pageable pageable) {
        log.debug("REST request to get a page of receptions");
        Page<ReceptionDTO> page = receptionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/receptions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    @PutMapping("/receptions/by-periode")
    public ResponseEntity<List<ReceptionDTO>> getAllreceptionsByPeriode(Pageable pageable, @RequestBody SearchDTO searchDTO) {
        log.debug("REST request to get a page of receptions");
        System.out.println("=======dateDebut===============");
        System.out.println(searchDTO.getDateDebut());
        System.out.println(searchDTO.getDateFin());
        System.out.println("======dateFin================");
        Page<ReceptionDTO> page = receptionService.findAllByPeriode(pageable, searchDTO.getDateDebut(), searchDTO.getDateFin());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/receptions/by-periode");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    @PutMapping("/receptions/by-client")
    public ResponseEntity<List<ReceptionDTO>> getAllreceptionsByPeriodeAndClient(Pageable pageable, @RequestBody SearchDTO searchDTO
            ) {
        log.debug("REST request to get a page of receptions");
        Page<ReceptionDTO> page = receptionService.findAllByClient(pageable, searchDTO.getClientId(),searchDTO.getDateDebut(), searchDTO.getDateFin());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/receptions/by-client");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    
     @PutMapping("/receptions/by-client-fournisseur")
    public ResponseEntity<List<ReceptionDTO>> getAllreceptionsByPeriodeAndClientAndFournisseur(Pageable pageable, @RequestBody SearchDTO searchDTO
           ) {
        log.debug("REST request to get a page of receptions");
        Page<ReceptionDTO> page = receptionService.findAllByClientAndFournisseur(pageable,searchDTO.getClientId(), searchDTO.getFournisseurId(),searchDTO.getDateDebut(), searchDTO.getDateFin());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/receptions/by-client-fournisseur");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    @PutMapping("/receptions/by-fournisseur")
    public ResponseEntity<List<ReceptionDTO>> getAllreceptionsByPeriodeFournisseur(Pageable pageable, @RequestBody SearchDTO searchDTO
            ) {
        log.debug("REST request to get a page of receptions");
        Page<ReceptionDTO> page = receptionService.findAllByFournisseur(pageable, searchDTO.getFournisseurId(),searchDTO.getDateDebut(), searchDTO.getDateFin());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/receptions/by-fournisseur");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    @GetMapping("/receptions/colis")
    public ResponseEntity<List<ColisDTO>> findColisByReception(@RequestParam(name = "receptionId") Long receptionId, Pageable pageable) {
        log.debug("REST request to get a page of receptions");
        Page<ColisDTO> page = receptionService.findColisByReception(pageable, receptionId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/receptions/colis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    @GetMapping("/receptions/colis-all")
    public ResponseEntity<List<ColisDTO>> findAllColis( Pageable pageable) {
        log.debug("REST request to get a page of receptions");
        Page<ColisDTO> page = receptionService.findAllColis(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/receptions/colis-all");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /receptions/:id : get the "id" reception.
     *
     * @param id the id of the ReceptionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ReceptionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/receptions/{id}")
    public ResponseEntity<ReceptionDTO> getreception(@PathVariable Long id) {
        log.debug("REST request to get reception : {}", id);
        Optional<ReceptionDTO> ReceptionDTO = receptionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ReceptionDTO);
    }

    /**
     * DELETE  /receptions/:id : delete the "id" reception.
     *
     * @param id the id of the ReceptionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/receptions/{id}")
    public ResponseEntity<Void> deletereception(@PathVariable Long id) {
        log.debug("REST request to delete reception : {}", id);
        receptionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @DeleteMapping("/receptions/colis/{id}")
    public ResponseEntity<Void> deleteColis(@PathVariable Long colisId) {
        log.debug("REST request to delete reception : {}", colisId);
        receptionService.deleteColis(colisId);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, colisId.toString())).build();
    }
    
    
    
    @DeleteMapping("/receptions/colis/{colisId}")
    public ResponseEntity<Void> deleteColis(@PathVariable Long colisId) {
        log.debug("REST request to delete reception : {}", colisId);
        receptionService.deleteColis(colisId);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, colisId.toString())).build();
    }
    
    
}
