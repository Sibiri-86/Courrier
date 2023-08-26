package bf.gov.courrier.web.rest;

import bf.gov.courrier.service.EmballageService;
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
public class EmballageResource {

    private final Logger log = LoggerFactory.getLogger(EmballageResource.class);

    private static final String ENTITY_NAME = "emballage";

    private final EmballageService emballageService;

    public EmballageResource(EmballageService emballageService) {
        this.emballageService = emballageService;
    }

    /**
     * POST  /emballages : Create a new emballage.
     *
     * @param emballageDto the emballageDto to create
     * @return the ResponseEntity with status 201 (Created) and with body the new emballageDto, or with status 400 (Bad Request) if the emballage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/emballages")
    public ResponseEntity<EmballageDTO> createEmballage(@RequestBody EmballageDTO emballageDto) throws URISyntaxException {
        log.debug("REST request to save Emballage : {}", emballageDto);
        if (emballageDto.getId() != null) {
            throw new BadRequestAlertException("A new emballage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmballageDTO result = emballageService.save(emballageDto);
        return ResponseEntity.created(new URI("/api/emballages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /emballages : Updates an existing emballage.
     *
     * @param emballageDto the emballageDto to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated emballageDto,
     * or with status 400 (Bad Request) if the emballageDto is not valid,
     * or with status 500 (Internal Server Error) if the emballageDto couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/emballages")
    public ResponseEntity<EmballageDTO> updateEmballage(@RequestBody EmballageDTO emballageDto) throws URISyntaxException {
        log.debug("REST request to update emballage : {}", emballageDto);
        if (emballageDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmballageDTO result = emballageService.save(emballageDto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, emballageDto.getId().toString()))
            .body(result);
    }

    /**
     * GET  /emballages : get all the emballages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of emballages in body
     */
    @GetMapping("/emballages")
    public ResponseEntity<List<EmballageDTO>> getAllEmballages(Pageable pageable) {
        log.debug("REST request to get a page of emballages");
        Page<EmballageDTO> page = emballageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/emballages");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /emballages/:id : get the "id" emballage.
     *
     * @param id the id of the agentDto to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the emballageDTO, or with status 404 (Not Found)
     */
    @GetMapping("/emballages/{id}")
    public ResponseEntity<EmballageDTO> getEmballage(@PathVariable Long id) {
        log.debug("REST request to get emballage : {}", id);
        Optional<EmballageDTO> emballageDTO = emballageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(emballageDTO);
    }

    /**
     * DELETE  /emballages/:id : delete the "id" emballage.
     *
     * @param id the id of the emballagesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/emballages/{id}")
    public ResponseEntity<Void> deleteEmballage(@PathVariable Long id) {
        log.debug("REST request to delete Emballage : {}", id);
        emballageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("/emballages/colis")
    public ResponseEntity<List<ColisDTO>> findColisByReception(@RequestParam(name = "emballageId") Long emballageId, Pageable pageable) {
        log.debug("REST request to get a page of receptions");
        Page<ColisDTO> page = emballageService.findColisByEmballage(pageable, emballageId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/emballages/colis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
   
    @DeleteMapping("/emballages/colis-retire/{colisId}")
    public ResponseEntity<Void> retireEmballageColis(@PathVariable Long colisId) {
        emballageService.retireEmballageColis(colisId);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, colisId.toString())).build();
    }
    
}
