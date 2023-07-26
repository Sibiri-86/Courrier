package bf.gov.courrier.web.rest;

import bf.gov.courrier.CourrierApp;

import bf.gov.courrier.domain.TailleBalle;
import bf.gov.courrier.repository.TailleBalleRepository;
import bf.gov.courrier.service.TailleBalleService;
import bf.gov.courrier.service.dto.TailleBalleDTO;
import bf.gov.courrier.service.mapper.TailleBalleMapper;
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
 * Test class for the TailleBalleResource REST controller.
 *
 * @see TailleBalleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CourrierApp.class)
public class TailleBalleResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Long DEFAULT_VOLUME = 1L;
    private static final Long UPDATED_VOLUME = 2L;

    @Autowired
    private TailleBalleRepository tailleBalleRepository;

    @Autowired
    private TailleBalleMapper tailleBalleMapper;

    @Autowired
    private TailleBalleService tailleBalleService;

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

    private MockMvc restTailleBalleMockMvc;

    private TailleBalle tailleBalle;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TailleBalleResource tailleBalleResource = new TailleBalleResource(tailleBalleService);
        this.restTailleBalleMockMvc = MockMvcBuilders.standaloneSetup(tailleBalleResource)
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
    public static TailleBalle createEntity(EntityManager em) {
        TailleBalle tailleBalle = new TailleBalle()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE)
            .volume(DEFAULT_VOLUME);
        return tailleBalle;
    }

    @Before
    public void initTest() {
        tailleBalle = createEntity(em);
    }

    @Test
    @Transactional
    public void createTailleBalle() throws Exception {
        int databaseSizeBeforeCreate = tailleBalleRepository.findAll().size();

        // Create the TailleBalle
        TailleBalleDTO tailleBalleDTO = tailleBalleMapper.toDto(tailleBalle);
        restTailleBalleMockMvc.perform(post("/api/taille-balles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tailleBalleDTO)))
            .andExpect(status().isCreated());

        // Validate the TailleBalle in the database
        List<TailleBalle> tailleBalleList = tailleBalleRepository.findAll();
        assertThat(tailleBalleList).hasSize(databaseSizeBeforeCreate + 1);
        TailleBalle testTailleBalle = tailleBalleList.get(tailleBalleList.size() - 1);
        assertThat(testTailleBalle.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testTailleBalle.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testTailleBalle.getVolume()).isEqualTo(DEFAULT_VOLUME);
    }

    @Test
    @Transactional
    public void createTailleBalleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tailleBalleRepository.findAll().size();

        // Create the TailleBalle with an existing ID
        tailleBalle.setId(1L);
        TailleBalleDTO tailleBalleDTO = tailleBalleMapper.toDto(tailleBalle);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTailleBalleMockMvc.perform(post("/api/taille-balles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tailleBalleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TailleBalle in the database
        List<TailleBalle> tailleBalleList = tailleBalleRepository.findAll();
        assertThat(tailleBalleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTailleBalles() throws Exception {
        // Initialize the database
        tailleBalleRepository.saveAndFlush(tailleBalle);

        // Get all the tailleBalleList
        restTailleBalleMockMvc.perform(get("/api/taille-balles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tailleBalle.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].volume").value(hasItem(DEFAULT_VOLUME.intValue())));
    }
    
    @Test
    @Transactional
    public void getTailleBalle() throws Exception {
        // Initialize the database
        tailleBalleRepository.saveAndFlush(tailleBalle);

        // Get the tailleBalle
        restTailleBalleMockMvc.perform(get("/api/taille-balles/{id}", tailleBalle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tailleBalle.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
            .andExpect(jsonPath("$.volume").value(DEFAULT_VOLUME.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTailleBalle() throws Exception {
        // Get the tailleBalle
        restTailleBalleMockMvc.perform(get("/api/taille-balles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTailleBalle() throws Exception {
        // Initialize the database
        tailleBalleRepository.saveAndFlush(tailleBalle);

        int databaseSizeBeforeUpdate = tailleBalleRepository.findAll().size();

        // Update the tailleBalle
        TailleBalle updatedTailleBalle = tailleBalleRepository.findById(tailleBalle.getId()).get();
        // Disconnect from session so that the updates on updatedTailleBalle are not directly saved in db
        em.detach(updatedTailleBalle);
        updatedTailleBalle
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE)
            .volume(UPDATED_VOLUME);
        TailleBalleDTO tailleBalleDTO = tailleBalleMapper.toDto(updatedTailleBalle);

        restTailleBalleMockMvc.perform(put("/api/taille-balles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tailleBalleDTO)))
            .andExpect(status().isOk());

        // Validate the TailleBalle in the database
        List<TailleBalle> tailleBalleList = tailleBalleRepository.findAll();
        assertThat(tailleBalleList).hasSize(databaseSizeBeforeUpdate);
        TailleBalle testTailleBalle = tailleBalleList.get(tailleBalleList.size() - 1);
        assertThat(testTailleBalle.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTailleBalle.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testTailleBalle.getVolume()).isEqualTo(UPDATED_VOLUME);
    }

    @Test
    @Transactional
    public void updateNonExistingTailleBalle() throws Exception {
        int databaseSizeBeforeUpdate = tailleBalleRepository.findAll().size();

        // Create the TailleBalle
        TailleBalleDTO tailleBalleDTO = tailleBalleMapper.toDto(tailleBalle);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTailleBalleMockMvc.perform(put("/api/taille-balles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tailleBalleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TailleBalle in the database
        List<TailleBalle> tailleBalleList = tailleBalleRepository.findAll();
        assertThat(tailleBalleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTailleBalle() throws Exception {
        // Initialize the database
        tailleBalleRepository.saveAndFlush(tailleBalle);

        int databaseSizeBeforeDelete = tailleBalleRepository.findAll().size();

        // Delete the tailleBalle
        restTailleBalleMockMvc.perform(delete("/api/taille-balles/{id}", tailleBalle.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TailleBalle> tailleBalleList = tailleBalleRepository.findAll();
        assertThat(tailleBalleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TailleBalle.class);
        TailleBalle tailleBalle1 = new TailleBalle();
        tailleBalle1.setId(1L);
        TailleBalle tailleBalle2 = new TailleBalle();
        tailleBalle2.setId(tailleBalle1.getId());
        assertThat(tailleBalle1).isEqualTo(tailleBalle2);
        tailleBalle2.setId(2L);
        assertThat(tailleBalle1).isNotEqualTo(tailleBalle2);
        tailleBalle1.setId(null);
        assertThat(tailleBalle1).isNotEqualTo(tailleBalle2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TailleBalleDTO.class);
        TailleBalleDTO tailleBalleDTO1 = new TailleBalleDTO();
        tailleBalleDTO1.setId(1L);
        TailleBalleDTO tailleBalleDTO2 = new TailleBalleDTO();
        assertThat(tailleBalleDTO1).isNotEqualTo(tailleBalleDTO2);
        tailleBalleDTO2.setId(tailleBalleDTO1.getId());
        assertThat(tailleBalleDTO1).isEqualTo(tailleBalleDTO2);
        tailleBalleDTO2.setId(2L);
        assertThat(tailleBalleDTO1).isNotEqualTo(tailleBalleDTO2);
        tailleBalleDTO1.setId(null);
        assertThat(tailleBalleDTO1).isNotEqualTo(tailleBalleDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tailleBalleMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tailleBalleMapper.fromId(null)).isNull();
    }
}
