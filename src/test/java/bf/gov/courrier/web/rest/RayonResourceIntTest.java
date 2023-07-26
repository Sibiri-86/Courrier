package bf.gov.courrier.web.rest;

import bf.gov.courrier.CourrierApp;

import bf.gov.courrier.domain.Rayon;
import bf.gov.courrier.repository.RayonRepository;
import bf.gov.courrier.service.RayonService;
import bf.gov.courrier.service.dto.RayonDTO;
import bf.gov.courrier.service.mapper.RayonMapper;
import bf.gov.courrier.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static bf.gov.courrier.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the RayonResource REST controller.
 *
 * @see RayonResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CourrierApp.class)
public class RayonResourceIntTest {

    private static final Long DEFAULT_NUMERO = 1L;
    private static final Long UPDATED_NUMERO = 2L;

    @Autowired
    private RayonRepository rayonRepository;

    @Autowired
    private RayonMapper rayonMapper;

    @Autowired
    private RayonService rayonService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restRayonMockMvc;

    private Rayon rayon;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RayonResource rayonResource = new RayonResource(rayonService);
        this.restRayonMockMvc = MockMvcBuilders.standaloneSetup(rayonResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rayon createEntity(EntityManager em) {
        Rayon rayon = new Rayon()
            .numero(DEFAULT_NUMERO);
        return rayon;
    }

    @Before
    public void initTest() {
        rayon = createEntity(em);
    }

    @Test
    @Transactional
    public void createRayon() throws Exception {
        int databaseSizeBeforeCreate = rayonRepository.findAll().size();

        // Create the Rayon
        RayonDTO rayonDTO = rayonMapper.toDto(rayon);
        restRayonMockMvc.perform(post("/api/rayons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rayonDTO)))
            .andExpect(status().isCreated());

        // Validate the Rayon in the database
        List<Rayon> rayonList = rayonRepository.findAll();
        assertThat(rayonList).hasSize(databaseSizeBeforeCreate + 1);
        Rayon testRayon = rayonList.get(rayonList.size() - 1);
        assertThat(testRayon.getNumero()).isEqualTo(DEFAULT_NUMERO);
    }

    @Test
    @Transactional
    public void createRayonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rayonRepository.findAll().size();

        // Create the Rayon with an existing ID
        rayon.setId(1L);
        RayonDTO rayonDTO = rayonMapper.toDto(rayon);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRayonMockMvc.perform(post("/api/rayons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rayonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Rayon in the database
        List<Rayon> rayonList = rayonRepository.findAll();
        assertThat(rayonList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRayons() throws Exception {
        // Initialize the database
        rayonRepository.saveAndFlush(rayon);

        // Get all the rayonList
        restRayonMockMvc.perform(get("/api/rayons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rayon.getId().intValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.intValue())));
    }
    
    @Test
    @Transactional
    public void getRayon() throws Exception {
        // Initialize the database
        rayonRepository.saveAndFlush(rayon);

        // Get the rayon
        restRayonMockMvc.perform(get("/api/rayons/{id}", rayon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rayon.getId().intValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingRayon() throws Exception {
        // Get the rayon
        restRayonMockMvc.perform(get("/api/rayons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRayon() throws Exception {
        // Initialize the database
        rayonRepository.saveAndFlush(rayon);

        int databaseSizeBeforeUpdate = rayonRepository.findAll().size();

        // Update the rayon
        Rayon updatedRayon = rayonRepository.findById(rayon.getId()).get();
        // Disconnect from session so that the updates on updatedRayon are not directly saved in db
        em.detach(updatedRayon);
        updatedRayon
            .numero(UPDATED_NUMERO);
        RayonDTO rayonDTO = rayonMapper.toDto(updatedRayon);

        restRayonMockMvc.perform(put("/api/rayons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rayonDTO)))
            .andExpect(status().isOk());

        // Validate the Rayon in the database
        List<Rayon> rayonList = rayonRepository.findAll();
        assertThat(rayonList).hasSize(databaseSizeBeforeUpdate);
        Rayon testRayon = rayonList.get(rayonList.size() - 1);
        assertThat(testRayon.getNumero()).isEqualTo(UPDATED_NUMERO);
    }

    @Test
    @Transactional
    public void updateNonExistingRayon() throws Exception {
        int databaseSizeBeforeUpdate = rayonRepository.findAll().size();

        // Create the Rayon
        RayonDTO rayonDTO = rayonMapper.toDto(rayon);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRayonMockMvc.perform(put("/api/rayons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rayonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Rayon in the database
        List<Rayon> rayonList = rayonRepository.findAll();
        assertThat(rayonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRayon() throws Exception {
        // Initialize the database
        rayonRepository.saveAndFlush(rayon);

        int databaseSizeBeforeDelete = rayonRepository.findAll().size();

        // Delete the rayon
        restRayonMockMvc.perform(delete("/api/rayons/{id}", rayon.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Rayon> rayonList = rayonRepository.findAll();
        assertThat(rayonList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Rayon.class);
        Rayon rayon1 = new Rayon();
        rayon1.setId(1L);
        Rayon rayon2 = new Rayon();
        rayon2.setId(rayon1.getId());
        assertThat(rayon1).isEqualTo(rayon2);
        rayon2.setId(2L);
        assertThat(rayon1).isNotEqualTo(rayon2);
        rayon1.setId(null);
        assertThat(rayon1).isNotEqualTo(rayon2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RayonDTO.class);
        RayonDTO rayonDTO1 = new RayonDTO();
        rayonDTO1.setId(1L);
        RayonDTO rayonDTO2 = new RayonDTO();
        assertThat(rayonDTO1).isNotEqualTo(rayonDTO2);
        rayonDTO2.setId(rayonDTO1.getId());
        assertThat(rayonDTO1).isEqualTo(rayonDTO2);
        rayonDTO2.setId(2L);
        assertThat(rayonDTO1).isNotEqualTo(rayonDTO2);
        rayonDTO1.setId(null);
        assertThat(rayonDTO1).isNotEqualTo(rayonDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(rayonMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(rayonMapper.fromId(null)).isNull();
    }
}
