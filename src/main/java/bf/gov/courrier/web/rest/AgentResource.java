package bf.gov.courrier.web.rest;
import bf.gov.courrier.service.AgentService;
import bf.gov.courrier.web.rest.errors.BadRequestAlertException;
import bf.gov.courrier.web.rest.util.HeaderUtil;
import bf.gov.courrier.web.rest.util.PaginationUtil;
import bf.gov.courrier.service.dto.AgentDTO;
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
public class AgentResource {

    private final Logger log = LoggerFactory.getLogger(AgentResource.class);

    private static final String ENTITY_NAME = "agent";

    private final AgentService agentService;

    public AgentResource(AgentService agentService) {
        this.agentService = agentService;
    }

    /**
     * POST  /agents : Create a new agent.
     *
     * @param agentDTO the agentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new agentDTO, or with status 400 (Bad Request) if the agent has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/agents")
    public ResponseEntity<AgentDTO> createAgent(@RequestBody AgentDTO agentDTO) throws URISyntaxException {
        log.debug("REST request to save Agent : {}", agentDTO);
        if (agentDTO.getId() != null) {
            throw new BadRequestAlertException("A new agent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AgentDTO result = agentService.save(agentDTO);
        return ResponseEntity.created(new URI("/api/agents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /agents : Updates an existing agent.
     *
     * @param agentDTO the agentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated agentDTO,
     * or with status 400 (Bad Request) if the agentDTO is not valid,
     * or with status 500 (Internal Server Error) if the agentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/agents")
    public ResponseEntity<AgentDTO> updateAgent(@RequestBody AgentDTO agentDTO) throws URISyntaxException {
        log.debug("REST request to update Agent : {}", agentDTO);
        if (agentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AgentDTO result = agentService.save(agentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, agentDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /agents : get all the agents.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of agents in body
     */
    @GetMapping("/agents")
    public ResponseEntity<List<AgentDTO>> getAllAgents(Pageable pageable) {
        log.debug("REST request to get a page of Agents");
        Page<AgentDTO> page = agentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/agents");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /agents/:id : get the "id" agent.
     *
     * @param id the id of the agentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the agentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/agents/{id}")
    public ResponseEntity<AgentDTO> getAgent(@PathVariable Long id) {
        log.debug("REST request to get Agent : {}", id);
        Optional<AgentDTO> agentDTO = agentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(agentDTO);
    }

    /**
     * DELETE  /agents/:id : delete the "id" agent.
     *
     * @param id the id of the agentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/agents/{id}")
    public ResponseEntity<Void> deleteAgent(@PathVariable Long id) {
        log.debug("REST request to delete Agent : {}", id);
        agentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
