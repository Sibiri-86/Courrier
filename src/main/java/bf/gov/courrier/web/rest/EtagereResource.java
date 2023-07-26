package bf.gov.courrier.web.rest;
import bf.gov.courrier.service.EtagereService;
import bf.gov.courrier.web.rest.errors.BadRequestAlertException;
import bf.gov.courrier.web.rest.util.HeaderUtil;
import bf.gov.courrier.web.rest.util.PaginationUtil;
import bf.gov.courrier.service.dto.EtagereDTO;
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

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Etagere.
 */
@RestController
@RequestMapping("/api")
public class EtagereResource {

    private final Logger log = LoggerFactory.getLogger(EtagereResource.class);

    private static final String ENTITY_NAME = "etagere";

    private final EtagereService etagereService;

    public EtagereResource(EtagereService etagereService) {
        this.etagereService = etagereService;
    }

    /**
     * POST  /etageres : Create a new etagere.
     *
     * @param etagereDTO the etagereDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new etagereDTO, or with status 400 (Bad Request) if the etagere has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/etageres")
    public ResponseEntity<EtagereDTO> createEtagere(@RequestBody EtagereDTO etagereDTO) throws URISyntaxException {
        log.debug("REST request to save Etagere : {}", etagereDTO);
        if (etagereDTO.getId() != null) {
            throw new BadRequestAlertException("A new etagere cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtagereDTO result = etagereService.save(etagereDTO);
        return ResponseEntity.created(new URI("/api/etageres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /etageres : Updates an existing etagere.
     *
     * @param etagereDTO the etagereDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated etagereDTO,
     * or with status 400 (Bad Request) if the etagereDTO is not valid,
     * or with status 500 (Internal Server Error) if the etagereDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/etageres")
    public ResponseEntity<EtagereDTO> updateEtagere(@RequestBody EtagereDTO etagereDTO) throws URISyntaxException {
        log.debug("REST request to update Etagere : {}", etagereDTO);
        if (etagereDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtagereDTO result = etagereService.save(etagereDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, etagereDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /etageres : get all the etageres.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of etageres in body
     */
    @GetMapping("/etageres")
    public ResponseEntity<List<EtagereDTO>> getAllEtageres(Pageable pageable) {
        log.debug("REST request to get a page of Etageres");
        Page<EtagereDTO> page = etagereService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/etageres");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /etageres/:id : get the "id" etagere.
     *
     * @param id the id of the etagereDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the etagereDTO, or with status 404 (Not Found)
     */
    @GetMapping("/etageres/{id}")
    public ResponseEntity<EtagereDTO> getEtagere(@PathVariable Long id) {
        log.debug("REST request to get Etagere : {}", id);
        Optional<EtagereDTO> etagereDTO = etagereService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etagereDTO);
    }

    /**
     * DELETE  /etageres/:id : delete the "id" etagere.
     *
     * @param id the id of the etagereDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/etageres/{id}")
    public ResponseEntity<Void> deleteEtagere(@PathVariable Long id) {
        log.debug("REST request to delete Etagere : {}", id);
        etagereService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
