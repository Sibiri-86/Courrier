package bf.gov.courrier.web.rest;

import bf.gov.courrier.service.ChargementService;
import bf.gov.courrier.service.dto.ChargementDTO;
import bf.gov.courrier.service.dto.ColisDTO;
import bf.gov.courrier.service.dto.EmballageDTO;
import bf.gov.courrier.web.rest.errors.BadRequestAlertException;
import bf.gov.courrier.web.rest.util.HeaderUtil;
import bf.gov.courrier.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Agent.
 */
@RestController
@RequestMapping("/api")
public class ChargementResource {

    private final Logger log = LoggerFactory.getLogger(ChargementResource.class);

    private static final String ENTITY_NAME = "chargement";

    private final ChargementService chargementService;

    public ChargementResource(ChargementService chargementService) {
        this.chargementService = chargementService;
    }

    /**
     * POST  /emballages : Create a new emballage.
     *
     * @param chargementDTO the chargementDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new chargementDTO, or with status 400 (Bad Request) if the emballage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/chargements")
    public ResponseEntity<ChargementDTO> createEmballage(@RequestBody ChargementDTO chargementDTO) throws URISyntaxException {
        log.debug("REST request to save Emballage : {}", chargementDTO);
        if (chargementDTO.getId() != null) {
            throw new BadRequestAlertException("A new emballage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChargementDTO result = chargementService.save(chargementDTO);
        return ResponseEntity.created(new URI("/api/emballages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /emballages : Updates an existing emballage.
     *
     * @param chargementDTO the chargementDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated chargementDTO,
     * or with status 400 (Bad Request) if the chargementDTO is not valid,
     * or with status 500 (Internal Server Error) if the chargementDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/chargements")
    public ResponseEntity<ChargementDTO> updateEmballage(@RequestBody ChargementDTO chargementDTO) throws URISyntaxException {
        log.debug("REST request to update emballage : {}", chargementDTO);
        if (chargementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChargementDTO result = chargementService.save(chargementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, chargementDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /chargements : get all the chargements.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of chargements in body
     */
    @GetMapping("/chargements")
    public ResponseEntity<List<ChargementDTO>> getAllEmballages(Pageable pageable) {
        log.debug("REST request to get a page of chargements");
        Page<ChargementDTO> page = chargementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/chargements");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /chargements/:id : get the "id" emballage.
     *
     * @param id the id of the agentDto to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the chargementDTO, or with status 404 (Not Found)
     */
    @GetMapping("/chargements/{id}")
    public ResponseEntity<ChargementDTO> getEmballage(@PathVariable Long id) {
        log.debug("REST request to get emballage : {}", id);
        Optional<ChargementDTO> chargementDTO = chargementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chargementDTO);
    }

    /**
     * DELETE  /chargements/:id : delete the "id" emballage.
     *
     * @param id the id of the emballagesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/chargements/{id}")
    public ResponseEntity<Void> deleteEmballage(@PathVariable Long id) {
        log.debug("REST request to delete Emballage : {}", id);
        chargementService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("/chargements/emballage")
    public ResponseEntity<List<EmballageDTO>> findEmballageByChargement(@RequestParam(name = "chargemetId") Long chargemetId, Pageable pageable) {
        log.debug("REST request to get a page of receptions");
        Page<EmballageDTO> page = chargementService.findEmballage(pageable, chargemetId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/chargements/colis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
   
    @DeleteMapping("/chargements/emballage-retire/{chargemetId}")
    public ResponseEntity<Void> retireEmballageColis(@PathVariable Long chargemetId) {
        chargementService.retireEmballage(chargemetId);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, chargemetId.toString())).build();
    }
    
}
