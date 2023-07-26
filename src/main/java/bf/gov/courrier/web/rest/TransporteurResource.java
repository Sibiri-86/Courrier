package bf.gov.courrier.web.rest;
import bf.gov.courrier.service.TransporteurService;
import bf.gov.courrier.web.rest.errors.BadRequestAlertException;
import bf.gov.courrier.web.rest.util.HeaderUtil;
import bf.gov.courrier.web.rest.util.PaginationUtil;
import bf.gov.courrier.service.dto.TransporteurDTO;
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
 * REST controller for managing Transporteur.
 */
@RestController
@RequestMapping("/api")
public class TransporteurResource {

    private final Logger log = LoggerFactory.getLogger(TransporteurResource.class);

    private static final String ENTITY_NAME = "transporteur";

    private final TransporteurService transporteurService;

    public TransporteurResource(TransporteurService transporteurService) {
        this.transporteurService = transporteurService;
    }

    /**
     * POST  /transporteurs : Create a new transporteur.
     *
     * @param transporteurDTO the transporteurDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new transporteurDTO, or with status 400 (Bad Request) if the transporteur has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/transporteurs")
    public ResponseEntity<TransporteurDTO> createTransporteur(@RequestBody TransporteurDTO transporteurDTO) throws URISyntaxException {
        log.debug("REST request to save Transporteur : {}", transporteurDTO);
        if (transporteurDTO.getId() != null) {
            throw new BadRequestAlertException("A new transporteur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransporteurDTO result = transporteurService.save(transporteurDTO);
        return ResponseEntity.created(new URI("/api/transporteurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /transporteurs : Updates an existing transporteur.
     *
     * @param transporteurDTO the transporteurDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated transporteurDTO,
     * or with status 400 (Bad Request) if the transporteurDTO is not valid,
     * or with status 500 (Internal Server Error) if the transporteurDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/transporteurs")
    public ResponseEntity<TransporteurDTO> updateTransporteur(@RequestBody TransporteurDTO transporteurDTO) throws URISyntaxException {
        log.debug("REST request to update Transporteur : {}", transporteurDTO);
        if (transporteurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransporteurDTO result = transporteurService.save(transporteurDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, transporteurDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /transporteurs : get all the transporteurs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of transporteurs in body
     */
    @GetMapping("/transporteurs")
    public ResponseEntity<List<TransporteurDTO>> getAllTransporteurs(Pageable pageable) {
        log.debug("REST request to get a page of Transporteurs");
        Page<TransporteurDTO> page = transporteurService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/transporteurs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /transporteurs/:id : get the "id" transporteur.
     *
     * @param id the id of the transporteurDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the transporteurDTO, or with status 404 (Not Found)
     */
    @GetMapping("/transporteurs/{id}")
    public ResponseEntity<TransporteurDTO> getTransporteur(@PathVariable Long id) {
        log.debug("REST request to get Transporteur : {}", id);
        Optional<TransporteurDTO> transporteurDTO = transporteurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transporteurDTO);
    }

    /**
     * DELETE  /transporteurs/:id : delete the "id" transporteur.
     *
     * @param id the id of the transporteurDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/transporteurs/{id}")
    public ResponseEntity<Void> deleteTransporteur(@PathVariable Long id) {
        log.debug("REST request to delete Transporteur : {}", id);
        transporteurService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
