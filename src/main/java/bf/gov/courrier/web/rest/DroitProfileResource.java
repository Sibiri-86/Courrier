package bf.gov.courrier.web.rest;
import bf.gov.courrier.service.AgentService;
import bf.gov.courrier.service.DroitProfileService;
import bf.gov.courrier.web.rest.errors.BadRequestAlertException;
import bf.gov.courrier.web.rest.util.HeaderUtil;
import bf.gov.courrier.web.rest.util.PaginationUtil;
import bf.gov.courrier.service.dto.AgentDTO;
import bf.gov.courrier.service.dto.DroitProfileDTO;
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
 * REST controller for managing Agent.
 */
@RestController
@RequestMapping("/api")
public class DroitProfileResource {

    private final Logger log = LoggerFactory.getLogger(DroitProfileResource.class);

    private static final String ENTITY_NAME = "droit_profile";

    private final DroitProfileService droitProfileService;

    public DroitProfileResource(DroitProfileService droitProfileService) {
        this.droitProfileService = droitProfileService;
    }

    /**
     * POST  /agents : Create a new agent.
     *
     * @param droitProfileDTO the agentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new agentDTO, or with status 400 (Bad Request) if the agent has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/droitProfiles")
    public ResponseEntity<DroitProfileDTO> createDroitProfile(@RequestBody DroitProfileDTO droitProfileDTO) throws URISyntaxException {
        log.debug("REST request to save Agent : {}", droitProfileDTO);
        if (droitProfileDTO.getId() != null) {
            throw new BadRequestAlertException("A new agent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DroitProfileDTO result = droitProfileService.save(droitProfileDTO);
        return ResponseEntity.created(new URI("/api/droitProfiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /agents : Updates an existing agent.
     *
     * @param droitProfileDTO the agentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated agentDTO,
     * or with status 400 (Bad Request) if the agentDTO is not valid,
     * or with status 500 (Internal Server Error) if the agentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/droitProfiles")
    public ResponseEntity<DroitProfileDTO> updateDroitProfile(@RequestBody DroitProfileDTO droitProfileDTO) throws URISyntaxException {
        log.debug("REST request to update Agent : {}", droitProfileDTO);
        if (droitProfileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DroitProfileDTO result = droitProfileService.save(droitProfileDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, droitProfileDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /agents : get all the agents.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of agents in body
     */
    @GetMapping("/droitProfiles")
    public ResponseEntity<List<DroitProfileDTO>> getAllDroitProfiles(Pageable pageable) {
        log.debug("REST request to get a page of Agents");
        Page<DroitProfileDTO> page = droitProfileService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/droitProfiles");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /agents/:id : get the "id" agent.
     *
     * @param id the id of the agentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the agentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/droitProfiles/{id}")
    public ResponseEntity<DroitProfileDTO> getDroitProfile(@PathVariable Long id) {
        log.debug("REST request to get Agent : {}", id);
        Optional<DroitProfileDTO> droitProfileDTO = droitProfileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(droitProfileDTO);
    }

    /**
     * DELETE  /agents/:id : delete the "id" agent.
     *
     * @param id the id of the agentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/droitProfiles/{id}")
    public ResponseEntity<Void> deleteDroitProfile(@PathVariable Long id) {
        log.debug("REST request to delete Agent : {}", id);
        droitProfileService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("/droitProfiles/profile")
    public ResponseEntity<List<DroitProfileDTO>> getAllAgentsBySite(@RequestParam Long profileId) {
         System.out.println("====================iii===="+profileId);
        log.debug("REST request to get a page of Agents");
        List<DroitProfileDTO> droitProfileDTOs = droitProfileService.findAllByProfile(profileId);
        return ResponseEntity.ok(droitProfileDTOs);
    }
}
