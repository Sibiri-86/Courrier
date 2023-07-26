package bf.gov.courrier.web.rest;

import bf.gov.courrier.CourrierApp;

import bf.gov.courrier.domain.Transitaire;
import bf.gov.courrier.repository.TransitaireRepository;
import bf.gov.courrier.service.TransitaireService;
import bf.gov.courrier.service.dto.TransitaireDTO;
import bf.gov.courrier.service.mapper.TransitaireMapper;
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
 * Test class for the TransitaireResource REST controller.
 *
 * @see TransitaireResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CourrierApp.class)
public class TransitaireResourceIntTest {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_TEL_1 = "AAAAAAAAAA";
    private static final String UPDATED_TEL_1 = "BBBBBBBBBB";

    private static final String DEFAULT_TEL_2 = "AAAAAAAAAA";
    private static final String UPDATED_TEL_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_PAYS_1 = "AAAAAAAAAA";
    private static final String UPDATED_CODE_PAYS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_PAYS_2 = "AAAAAAAAAA";
    private static final String UPDATED_CODE_PAYS_2 = "BBBBBBBBBB";

    @Autowired
    private TransitaireRepository transitaireRepository;

    @Autowired
    private TransitaireMapper transitaireMapper;

    @Autowired
    private TransitaireService transitaireService;

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

    private MockMvc restTransitaireMockMvc;

    private Transitaire transitaire;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TransitaireResource transitaireResource = new TransitaireResource(transitaireService);
        this.restTransitaireMockMvc = MockMvcBuilders.standaloneSetup(transitaireResource)
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
    public static Transitaire createEntity(EntityManager em) {
        Transitaire transitaire = new Transitaire()
            .nom(DEFAULT_NOM)
            .email(DEFAULT_EMAIL)
            .tel1(DEFAULT_TEL_1)
            .tel2(DEFAULT_TEL_2)
            .codePays1(DEFAULT_CODE_PAYS_1)
            .codePays2(DEFAULT_CODE_PAYS_2);
        return transitaire;
    }

    @Before
    public void initTest() {
        transitaire = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransitaire() throws Exception {
        int databaseSizeBeforeCreate = transitaireRepository.findAll().size();

        // Create the Transitaire
        TransitaireDTO transitaireDTO = transitaireMapper.toDto(transitaire);
        restTransitaireMockMvc.perform(post("/api/transitaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transitaireDTO)))
            .andExpect(status().isCreated());

        // Validate the Transitaire in the database
        List<Transitaire> transitaireList = transitaireRepository.findAll();
        assertThat(transitaireList).hasSize(databaseSizeBeforeCreate + 1);
        Transitaire testTransitaire = transitaireList.get(transitaireList.size() - 1);
        assertThat(testTransitaire.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testTransitaire.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testTransitaire.getTel1()).isEqualTo(DEFAULT_TEL_1);
        assertThat(testTransitaire.getTel2()).isEqualTo(DEFAULT_TEL_2);
        assertThat(testTransitaire.getCodePays1()).isEqualTo(DEFAULT_CODE_PAYS_1);
        assertThat(testTransitaire.getCodePays2()).isEqualTo(DEFAULT_CODE_PAYS_2);
    }

    @Test
    @Transactional
    public void createTransitaireWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transitaireRepository.findAll().size();

        // Create the Transitaire with an existing ID
        transitaire.setId(1L);
        TransitaireDTO transitaireDTO = transitaireMapper.toDto(transitaire);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransitaireMockMvc.perform(post("/api/transitaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transitaireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Transitaire in the database
        List<Transitaire> transitaireList = transitaireRepository.findAll();
        assertThat(transitaireList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTransitaires() throws Exception {
        // Initialize the database
        transitaireRepository.saveAndFlush(transitaire);

        // Get all the transitaireList
        restTransitaireMockMvc.perform(get("/api/transitaires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transitaire.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].tel1").value(hasItem(DEFAULT_TEL_1.toString())))
            .andExpect(jsonPath("$.[*].tel2").value(hasItem(DEFAULT_TEL_2.toString())))
            .andExpect(jsonPath("$.[*].codePays1").value(hasItem(DEFAULT_CODE_PAYS_1.toString())))
            .andExpect(jsonPath("$.[*].codePays2").value(hasItem(DEFAULT_CODE_PAYS_2.toString())));
    }
    
    @Test
    @Transactional
    public void getTransitaire() throws Exception {
        // Initialize the database
        transitaireRepository.saveAndFlush(transitaire);

        // Get the transitaire
        restTransitaireMockMvc.perform(get("/api/transitaires/{id}", transitaire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(transitaire.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.tel1").value(DEFAULT_TEL_1.toString()))
            .andExpect(jsonPath("$.tel2").value(DEFAULT_TEL_2.toString()))
            .andExpect(jsonPath("$.codePays1").value(DEFAULT_CODE_PAYS_1.toString()))
            .andExpect(jsonPath("$.codePays2").value(DEFAULT_CODE_PAYS_2.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTransitaire() throws Exception {
        // Get the transitaire
        restTransitaireMockMvc.perform(get("/api/transitaires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransitaire() throws Exception {
        // Initialize the database
        transitaireRepository.saveAndFlush(transitaire);

        int databaseSizeBeforeUpdate = transitaireRepository.findAll().size();

        // Update the transitaire
        Transitaire updatedTransitaire = transitaireRepository.findById(transitaire.getId()).get();
        // Disconnect from session so that the updates on updatedTransitaire are not directly saved in db
        em.detach(updatedTransitaire);
        updatedTransitaire
            .nom(UPDATED_NOM)
            .email(UPDATED_EMAIL)
            .tel1(UPDATED_TEL_1)
            .tel2(UPDATED_TEL_2)
            .codePays1(UPDATED_CODE_PAYS_1)
            .codePays2(UPDATED_CODE_PAYS_2);
        TransitaireDTO transitaireDTO = transitaireMapper.toDto(updatedTransitaire);

        restTransitaireMockMvc.perform(put("/api/transitaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transitaireDTO)))
            .andExpect(status().isOk());

        // Validate the Transitaire in the database
        List<Transitaire> transitaireList = transitaireRepository.findAll();
        assertThat(transitaireList).hasSize(databaseSizeBeforeUpdate);
        Transitaire testTransitaire = transitaireList.get(transitaireList.size() - 1);
        assertThat(testTransitaire.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testTransitaire.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testTransitaire.getTel1()).isEqualTo(UPDATED_TEL_1);
        assertThat(testTransitaire.getTel2()).isEqualTo(UPDATED_TEL_2);
        assertThat(testTransitaire.getCodePays1()).isEqualTo(UPDATED_CODE_PAYS_1);
        assertThat(testTransitaire.getCodePays2()).isEqualTo(UPDATED_CODE_PAYS_2);
    }

    @Test
    @Transactional
    public void updateNonExistingTransitaire() throws Exception {
        int databaseSizeBeforeUpdate = transitaireRepository.findAll().size();

        // Create the Transitaire
        TransitaireDTO transitaireDTO = transitaireMapper.toDto(transitaire);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransitaireMockMvc.perform(put("/api/transitaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transitaireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Transitaire in the database
        List<Transitaire> transitaireList = transitaireRepository.findAll();
        assertThat(transitaireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransitaire() throws Exception {
        // Initialize the database
        transitaireRepository.saveAndFlush(transitaire);

        int databaseSizeBeforeDelete = transitaireRepository.findAll().size();

        // Delete the transitaire
        restTransitaireMockMvc.perform(delete("/api/transitaires/{id}", transitaire.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Transitaire> transitaireList = transitaireRepository.findAll();
        assertThat(transitaireList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Transitaire.class);
        Transitaire transitaire1 = new Transitaire();
        transitaire1.setId(1L);
        Transitaire transitaire2 = new Transitaire();
        transitaire2.setId(transitaire1.getId());
        assertThat(transitaire1).isEqualTo(transitaire2);
        transitaire2.setId(2L);
        assertThat(transitaire1).isNotEqualTo(transitaire2);
        transitaire1.setId(null);
        assertThat(transitaire1).isNotEqualTo(transitaire2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransitaireDTO.class);
        TransitaireDTO transitaireDTO1 = new TransitaireDTO();
        transitaireDTO1.setId(1L);
        TransitaireDTO transitaireDTO2 = new TransitaireDTO();
        assertThat(transitaireDTO1).isNotEqualTo(transitaireDTO2);
        transitaireDTO2.setId(transitaireDTO1.getId());
        assertThat(transitaireDTO1).isEqualTo(transitaireDTO2);
        transitaireDTO2.setId(2L);
        assertThat(transitaireDTO1).isNotEqualTo(transitaireDTO2);
        transitaireDTO1.setId(null);
        assertThat(transitaireDTO1).isNotEqualTo(transitaireDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(transitaireMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(transitaireMapper.fromId(null)).isNull();
    }
}
