package bf.gov.courrier.web.rest;
import bf.gov.courrier.service.NatureMarchandService;
import bf.gov.courrier.web.rest.errors.BadRequestAlertException;
import bf.gov.courrier.web.rest.util.HeaderUtil;
import bf.gov.courrier.web.rest.util.PaginationUtil;
import bf.gov.courrier.service.dto.NatureMarchandDTO;
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
 * REST controller for managing NatureMarchand.
 */
@RestController
@RequestMapping("/api")
public class NatureMarchandResource {

    private final Logger log = LoggerFactory.getLogger(NatureMarchandResource.class);

    private static final String ENTITY_NAME = "natureMarchand";

    private final NatureMarchandService natureMarchandService;

    public NatureMarchandResource(NatureMarchandService natureMarchandService) {
        this.natureMarchandService = natureMarchandService;
    }

    /**
     * POST  /nature-marchands : Create a new natureMarchand.
     *
     * @param natureMarchandDTO the natureMarchandDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new natureMarchandDTO, or with status 400 (Bad Request) if the natureMarchand has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nature-marchands")
    public ResponseEntity<NatureMarchandDTO> createNatureMarchand(@RequestBody NatureMarchandDTO natureMarchandDTO) throws URISyntaxException {
        log.debug("REST request to save NatureMarchand : {}", natureMarchandDTO);
        if (natureMarchandDTO.getId() != null) {
            throw new BadRequestAlertException("A new natureMarchand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NatureMarchandDTO result = natureMarchandService.save(natureMarchandDTO);
        return ResponseEntity.created(new URI("/api/nature-marchands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nature-marchands : Updates an existing natureMarchand.
     *
     * @param natureMarchandDTO the natureMarchandDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated natureMarchandDTO,
     * or with status 400 (Bad Request) if the natureMarchandDTO is not valid,
     * or with status 500 (Internal Server Error) if the natureMarchandDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nature-marchands")
    public ResponseEntity<NatureMarchandDTO> updateNatureMarchand(@RequestBody NatureMarchandDTO natureMarchandDTO) throws URISyntaxException {
        log.debug("REST request to update NatureMarchand : {}", natureMarchandDTO);
        if (natureMarchandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NatureMarchandDTO result = natureMarchandService.save(natureMarchandDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, natureMarchandDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nature-marchands : get all the natureMarchands.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of natureMarchands in body
     */
    @GetMapping("/nature-marchands")
    public ResponseEntity<List<NatureMarchandDTO>> getAllNatureMarchands(Pageable pageable) {
        log.debug("REST request to get a page of NatureMarchands");
        Page<NatureMarchandDTO> page = natureMarchandService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/nature-marchands");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /nature-marchands/:id : get the "id" natureMarchand.
     *
     * @param id the id of the natureMarchandDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the natureMarchandDTO, or with status 404 (Not Found)
     */
    @GetMapping("/nature-marchands/{id}")
    public ResponseEntity<NatureMarchandDTO> getNatureMarchand(@PathVariable Long id) {
        log.debug("REST request to get NatureMarchand : {}", id);
        Optional<NatureMarchandDTO> natureMarchandDTO = natureMarchandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(natureMarchandDTO);
    }

    /**
     * DELETE  /nature-marchands/:id : delete the "id" natureMarchand.
     *
     * @param id the id of the natureMarchandDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nature-marchands/{id}")
    public ResponseEntity<Void> deleteNatureMarchand(@PathVariable Long id) {
        log.debug("REST request to delete NatureMarchand : {}", id);
        natureMarchandService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
