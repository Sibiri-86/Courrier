package bf.gov.courrier.web.rest;
import bf.gov.courrier.service.RayonService;
import bf.gov.courrier.web.rest.errors.BadRequestAlertException;
import bf.gov.courrier.web.rest.util.HeaderUtil;
import bf.gov.courrier.web.rest.util.PaginationUtil;
import bf.gov.courrier.service.dto.RayonDTO;
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
 * REST controller for managing Rayon.
 */
@RestController
@RequestMapping("/api")
public class RayonResource {

    private final Logger log = LoggerFactory.getLogger(RayonResource.class);

    private static final String ENTITY_NAME = "rayon";

    private final RayonService rayonService;

    public RayonResource(RayonService rayonService) {
        this.rayonService = rayonService;
    }

    /**
     * POST  /rayons : Create a new rayon.
     *
     * @param rayonDTO the rayonDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rayonDTO, or with status 400 (Bad Request) if the rayon has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rayons")
    public ResponseEntity<RayonDTO> createRayon(@RequestBody RayonDTO rayonDTO) throws URISyntaxException {
        log.debug("REST request to save Rayon : {}", rayonDTO);
        if (rayonDTO.getId() != null) {
            throw new BadRequestAlertException("A new rayon cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RayonDTO result = rayonService.save(rayonDTO);
        return ResponseEntity.created(new URI("/api/rayons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rayons : Updates an existing rayon.
     *
     * @param rayonDTO the rayonDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rayonDTO,
     * or with status 400 (Bad Request) if the rayonDTO is not valid,
     * or with status 500 (Internal Server Error) if the rayonDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rayons")
    public ResponseEntity<RayonDTO> updateRayon(@RequestBody RayonDTO rayonDTO) throws URISyntaxException {
        log.debug("REST request to update Rayon : {}", rayonDTO);
        if (rayonDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RayonDTO result = rayonService.save(rayonDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rayonDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rayons : get all the rayons.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of rayons in body
     */
    @GetMapping("/rayons")
    public ResponseEntity<List<RayonDTO>> getAllRayons(Pageable pageable) {
        log.debug("REST request to get a page of Rayons");
        Page<RayonDTO> page = rayonService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rayons");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /rayons/:id : get the "id" rayon.
     *
     * @param id the id of the rayonDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rayonDTO, or with status 404 (Not Found)
     */
    @GetMapping("/rayons/{id}")
    public ResponseEntity<RayonDTO> getRayon(@PathVariable Long id) {
        log.debug("REST request to get Rayon : {}", id);
        Optional<RayonDTO> rayonDTO = rayonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rayonDTO);
    }

    /**
     * DELETE  /rayons/:id : delete the "id" rayon.
     *
     * @param id the id of the rayonDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rayons/{id}")
    public ResponseEntity<Void> deleteRayon(@PathVariable Long id) {
        log.debug("REST request to delete Rayon : {}", id);
        rayonService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
