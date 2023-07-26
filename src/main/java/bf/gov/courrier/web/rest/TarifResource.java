package bf.gov.courrier.web.rest;
import bf.gov.courrier.service.TarifService;
import bf.gov.courrier.web.rest.errors.BadRequestAlertException;
import bf.gov.courrier.web.rest.util.HeaderUtil;
import bf.gov.courrier.web.rest.util.PaginationUtil;
import bf.gov.courrier.service.dto.TarifDTO;
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
 * REST controller for managing Tarif.
 */
@RestController
@RequestMapping("/api")
public class TarifResource {

    private final Logger log = LoggerFactory.getLogger(TarifResource.class);

    private static final String ENTITY_NAME = "tarif";

    private final TarifService tarifService;

    public TarifResource(TarifService tarifService) {
        this.tarifService = tarifService;
    }

    /**
     * POST  /tarifs : Create a new tarif.
     *
     * @param tarifDTO the tarifDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tarifDTO, or with status 400 (Bad Request) if the tarif has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tarifs")
    public ResponseEntity<TarifDTO> createTarif(@RequestBody TarifDTO tarifDTO) throws URISyntaxException {
        log.debug("REST request to save Tarif : {}", tarifDTO);
        if (tarifDTO.getId() != null) {
            throw new BadRequestAlertException("A new tarif cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TarifDTO result = tarifService.save(tarifDTO);
        return ResponseEntity.created(new URI("/api/tarifs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tarifs : Updates an existing tarif.
     *
     * @param tarifDTO the tarifDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tarifDTO,
     * or with status 400 (Bad Request) if the tarifDTO is not valid,
     * or with status 500 (Internal Server Error) if the tarifDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tarifs")
    public ResponseEntity<TarifDTO> updateTarif(@RequestBody TarifDTO tarifDTO) throws URISyntaxException {
        log.debug("REST request to update Tarif : {}", tarifDTO);
        if (tarifDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TarifDTO result = tarifService.save(tarifDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tarifDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tarifs : get all the tarifs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tarifs in body
     */
    @GetMapping("/tarifs")
    public ResponseEntity<List<TarifDTO>> getAllTarifs(Pageable pageable) {
        log.debug("REST request to get a page of Tarifs");
        Page<TarifDTO> page = tarifService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tarifs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tarifs/:id : get the "id" tarif.
     *
     * @param id the id of the tarifDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tarifDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tarifs/{id}")
    public ResponseEntity<TarifDTO> getTarif(@PathVariable Long id) {
        log.debug("REST request to get Tarif : {}", id);
        Optional<TarifDTO> tarifDTO = tarifService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tarifDTO);
    }

    /**
     * DELETE  /tarifs/:id : delete the "id" tarif.
     *
     * @param id the id of the tarifDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tarifs/{id}")
    public ResponseEntity<Void> deleteTarif(@PathVariable Long id) {
        log.debug("REST request to delete Tarif : {}", id);
        tarifService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
