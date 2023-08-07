package bf.gov.courrier.web.rest;
import bf.gov.courrier.service.FournisseurService;
import bf.gov.courrier.web.rest.errors.BadRequestAlertException;
import bf.gov.courrier.web.rest.util.HeaderUtil;
import bf.gov.courrier.web.rest.util.PaginationUtil;
import bf.gov.courrier.service.dto.FournisseurDTO;
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
 * REST controller for managing Fournisseur.
 */
@RestController
@RequestMapping("/api")
public class FournisseurResource {

    private final Logger log = LoggerFactory.getLogger(FournisseurResource.class);

    private static final String ENTITY_NAME = "fournisseur";

    private final FournisseurService fournisseurService;

    public FournisseurResource(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    /**
     * POST  /fournisseurs : Create a new fournisseur.
     *
     * @param fournisseurDTO the fournisseurDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fournisseurDTO, or with status 400 (Bad Request) if the fournisseur has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fournisseurs")
    public ResponseEntity<FournisseurDTO> createFournisseur(@RequestBody FournisseurDTO fournisseurDTO) throws URISyntaxException {
        log.debug("REST request to save Fournisseur : {}", fournisseurDTO);
        if (fournisseurDTO.getId() != null) {
            throw new BadRequestAlertException("A new fournisseur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FournisseurDTO result = fournisseurService.save(fournisseurDTO);
        return ResponseEntity.created(new URI("/api/fournisseurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fournisseurs : Updates an existing fournisseur.
     *
     * @param fournisseurDTO the fournisseurDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fournisseurDTO,
     * or with status 400 (Bad Request) if the fournisseurDTO is not valid,
     * or with status 500 (Internal Server Error) if the fournisseurDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fournisseurs")
    public ResponseEntity<FournisseurDTO> updateFournisseur(@RequestBody FournisseurDTO fournisseurDTO) throws URISyntaxException {
        log.debug("REST request to update Fournisseur : {}", fournisseurDTO);
        if (fournisseurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FournisseurDTO result = fournisseurService.save(fournisseurDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fournisseurDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fournisseurs : get all the fournisseurs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of fournisseurs in body
     */
    @GetMapping("/fournisseurs")
    public ResponseEntity<List<FournisseurDTO>> getAllFournisseurs(Pageable pageable) {
        log.debug("REST request to get a page of Fournisseurs");
        Page<FournisseurDTO> page = fournisseurService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fournisseurs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /fournisseurs/:id : get the "id" fournisseur.
     *
     * @param id the id of the fournisseurDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fournisseurDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fournisseurs/{id}")
    public ResponseEntity<FournisseurDTO> getFournisseur(@PathVariable Long id) {
        log.debug("REST request to get Fournisseur : {}", id);
        Optional<FournisseurDTO> fournisseurDTO = fournisseurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fournisseurDTO);
    }

    /**
     * DELETE  /fournisseurs/:id : delete the "id" fournisseur.
     *
     * @param id the id of the fournisseurDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fournisseurs/{id}")
    public ResponseEntity<Void> deleteFournisseur(@PathVariable Long id) {
        log.debug("REST request to delete Fournisseur : {}", id);
        fournisseurService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("/fournisseurs/pays")
    public ResponseEntity<List<FournisseurDTO>> getAllAgentsByPays(@RequestParam Long paysId) {
        log.debug("REST request to get a page of Agents");
        List<FournisseurDTO> clients = fournisseurService.findAllByPays(paysId);
        return ResponseEntity.ok(clients);
    }
}
