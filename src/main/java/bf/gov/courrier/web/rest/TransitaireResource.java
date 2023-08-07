package bf.gov.courrier.web.rest;
import bf.gov.courrier.service.TransitaireService;
import bf.gov.courrier.web.rest.errors.BadRequestAlertException;
import bf.gov.courrier.web.rest.util.HeaderUtil;
import bf.gov.courrier.web.rest.util.PaginationUtil;
import bf.gov.courrier.service.dto.TransitaireDTO;
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
 * REST controller for managing Transitaire.
 */
@RestController
@RequestMapping("/api")
public class TransitaireResource {

    private final Logger log = LoggerFactory.getLogger(TransitaireResource.class);

    private static final String ENTITY_NAME = "transitaire";

    private final TransitaireService transitaireService;

    public TransitaireResource(TransitaireService transitaireService) {
        this.transitaireService = transitaireService;
    }

    /**
     * POST  /transitaires : Create a new transitaire.
     *
     * @param transitaireDTO the transitaireDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new transitaireDTO, or with status 400 (Bad Request) if the transitaire has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/transitaires")
    public ResponseEntity<TransitaireDTO> createTransitaire(@RequestBody TransitaireDTO transitaireDTO) throws URISyntaxException {
        log.debug("REST request to save Transitaire : {}", transitaireDTO);
        if (transitaireDTO.getId() != null) {
            throw new BadRequestAlertException("A new transitaire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransitaireDTO result = transitaireService.save(transitaireDTO);
        return ResponseEntity.created(new URI("/api/transitaires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /transitaires : Updates an existing transitaire.
     *
     * @param transitaireDTO the transitaireDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated transitaireDTO,
     * or with status 400 (Bad Request) if the transitaireDTO is not valid,
     * or with status 500 (Internal Server Error) if the transitaireDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/transitaires")
    public ResponseEntity<TransitaireDTO> updateTransitaire(@RequestBody TransitaireDTO transitaireDTO) throws URISyntaxException {
        log.debug("REST request to update Transitaire : {}", transitaireDTO);
        if (transitaireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransitaireDTO result = transitaireService.save(transitaireDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, transitaireDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /transitaires : get all the transitaires.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of transitaires in body
     */
    @GetMapping("/transitaires")
    public ResponseEntity<List<TransitaireDTO>> getAllTransitaires(Pageable pageable) {
        log.debug("REST request to get a page of Transitaires");
        Page<TransitaireDTO> page = transitaireService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/transitaires");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /transitaires/:id : get the "id" transitaire.
     *
     * @param id the id of the transitaireDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the transitaireDTO, or with status 404 (Not Found)
     */
    @GetMapping("/transitaires/{id}")
    public ResponseEntity<TransitaireDTO> getTransitaire(@PathVariable Long id) {
        log.debug("REST request to get Transitaire : {}", id);
        Optional<TransitaireDTO> transitaireDTO = transitaireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transitaireDTO);
    }

    /**
     * DELETE  /transitaires/:id : delete the "id" transitaire.
     *
     * @param id the id of the transitaireDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/transitaires/{id}")
    public ResponseEntity<Void> deleteTransitaire(@PathVariable Long id) {
        log.debug("REST request to delete Transitaire : {}", id);
        transitaireService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("/transitaires/pays")
    public ResponseEntity<List<TransitaireDTO>> getAllAgentsByPays(@RequestParam Long paysId) {
        log.debug("REST request to get a page of Agents");
        List<TransitaireDTO> clients = transitaireService.findAllByPays(paysId);
        return ResponseEntity.ok(clients);
    }
}
