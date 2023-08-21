package bf.gov.courrier.web.rest;
import bf.gov.courrier.service.ProfileService;
import bf.gov.courrier.service.TailleBalleService;
import bf.gov.courrier.web.rest.errors.BadRequestAlertException;
import bf.gov.courrier.web.rest.util.HeaderUtil;
import bf.gov.courrier.web.rest.util.PaginationUtil;
import bf.gov.courrier.service.dto.ProfileDTO;
import bf.gov.courrier.service.dto.TailleBalleDTO;
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
 * REST controller for managing TailleBalle.
 */
@RestController
@RequestMapping("/api")
public class ProfileResource {

    private final Logger log = LoggerFactory.getLogger(TailleBalleResource.class);

    private static final String ENTITY_NAME = "profile";

    private final ProfileService profileService;

    public ProfileResource(ProfileService profileService) {
        this.profileService = profileService;
    }

    /**
     * POST  /taille-balles : Create a new tailleBalle.
     *
     * @param profileDTO the tailleBalleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tailleBalleDTO, or with status 400 (Bad Request) if the tailleBalle has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/profiles")
    public ResponseEntity<ProfileDTO> createProfile(@RequestBody ProfileDTO profileDTO) throws URISyntaxException {
        log.debug("REST request to save TailleBalle : {}", profileDTO);
        if (profileDTO.getId() != null) {
            throw new BadRequestAlertException("A new tailleBalle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProfileDTO result = profileService.save(profileDTO);
        return ResponseEntity.created(new URI("/api/profiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /taille-balles : Updates an existing tailleBalle.
     *
     * @param profileDTO the tailleBalleDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tailleBalleDTO,
     * or with status 400 (Bad Request) if the tailleBalleDTO is not valid,
     * or with status 500 (Internal Server Error) if the tailleBalleDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/profiles")
    public ResponseEntity<ProfileDTO> updateTailleBalle(@RequestBody ProfileDTO profileDTO) throws URISyntaxException {
        log.debug("REST request to update TailleBalle : {}", profileDTO);
        if (profileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProfileDTO result = profileService.save(profileDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, profileDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /taille-balles : get all the tailleBalles.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tailleBalles in body
     */
    @GetMapping("/profiles")
    public ResponseEntity<List<ProfileDTO>> getAllProfiles(Pageable pageable) {
        log.debug("REST request to get a page of TailleBalles");
        Page<ProfileDTO> page = profileService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/profiles");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /taille-balles/:id : get the "id" tailleBalle.
     *
     * @param id the id of the tailleBalleDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tailleBalleDTO, or with status 404 (Not Found)
     */
    @GetMapping("/profiles/{id}")
    public ResponseEntity<ProfileDTO> getTailleBalle(@PathVariable Long id) {
        log.debug("REST request to get TailleBalle : {}", id);
        Optional<ProfileDTO> profileDTO = profileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(profileDTO);
    }

    /**
     * DELETE  /taille-balles/:id : delete the "id" tailleBalle.
     *
     * @param id the id of the tailleBalleDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/profiles/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        log.debug("REST request to delete TailleBalle : {}", id);
        profileService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
