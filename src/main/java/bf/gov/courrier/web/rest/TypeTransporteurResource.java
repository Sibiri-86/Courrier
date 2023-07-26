package bf.gov.courrier.web.rest;
import bf.gov.courrier.service.TypeTransporteurService;
import bf.gov.courrier.web.rest.errors.BadRequestAlertException;
import bf.gov.courrier.web.rest.util.HeaderUtil;
import bf.gov.courrier.web.rest.util.PaginationUtil;
import bf.gov.courrier.service.dto.TypeTransporteurDTO;
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
 * REST controller for managing TypeTransporteur.
 */
@RestController
@RequestMapping("/api")
public class TypeTransporteurResource {

    private final Logger log = LoggerFactory.getLogger(TypeTransporteurResource.class);

    private static final String ENTITY_NAME = "typeTransporteur";

    private final TypeTransporteurService typeTransporteurService;

    public TypeTransporteurResource(TypeTransporteurService typeTransporteurService) {
        this.typeTransporteurService = typeTransporteurService;
    }

    /**
     * POST  /type-transporteurs : Create a new typeTransporteur.
     *
     * @param typeTransporteurDTO the typeTransporteurDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new typeTransporteurDTO, or with status 400 (Bad Request) if the typeTransporteur has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/type-transporteurs")
    public ResponseEntity<TypeTransporteurDTO> createTypeTransporteur(@RequestBody TypeTransporteurDTO typeTransporteurDTO) throws URISyntaxException {
        log.debug("REST request to save TypeTransporteur : {}", typeTransporteurDTO);
        if (typeTransporteurDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeTransporteur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeTransporteurDTO result = typeTransporteurService.save(typeTransporteurDTO);
        return ResponseEntity.created(new URI("/api/type-transporteurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /type-transporteurs : Updates an existing typeTransporteur.
     *
     * @param typeTransporteurDTO the typeTransporteurDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated typeTransporteurDTO,
     * or with status 400 (Bad Request) if the typeTransporteurDTO is not valid,
     * or with status 500 (Internal Server Error) if the typeTransporteurDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/type-transporteurs")
    public ResponseEntity<TypeTransporteurDTO> updateTypeTransporteur(@RequestBody TypeTransporteurDTO typeTransporteurDTO) throws URISyntaxException {
        log.debug("REST request to update TypeTransporteur : {}", typeTransporteurDTO);
        if (typeTransporteurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeTransporteurDTO result = typeTransporteurService.save(typeTransporteurDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, typeTransporteurDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /type-transporteurs : get all the typeTransporteurs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of typeTransporteurs in body
     */
    @GetMapping("/type-transporteurs")
    public ResponseEntity<List<TypeTransporteurDTO>> getAllTypeTransporteurs(Pageable pageable) {
        log.debug("REST request to get a page of TypeTransporteurs");
        Page<TypeTransporteurDTO> page = typeTransporteurService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/type-transporteurs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /type-transporteurs/:id : get the "id" typeTransporteur.
     *
     * @param id the id of the typeTransporteurDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the typeTransporteurDTO, or with status 404 (Not Found)
     */
    @GetMapping("/type-transporteurs/{id}")
    public ResponseEntity<TypeTransporteurDTO> getTypeTransporteur(@PathVariable Long id) {
        log.debug("REST request to get TypeTransporteur : {}", id);
        Optional<TypeTransporteurDTO> typeTransporteurDTO = typeTransporteurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeTransporteurDTO);
    }

    /**
     * DELETE  /type-transporteurs/:id : delete the "id" typeTransporteur.
     *
     * @param id the id of the typeTransporteurDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/type-transporteurs/{id}")
    public ResponseEntity<Void> deleteTypeTransporteur(@PathVariable Long id) {
        log.debug("REST request to delete TypeTransporteur : {}", id);
        typeTransporteurService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
