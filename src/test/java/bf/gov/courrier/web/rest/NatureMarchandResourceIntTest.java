package bf.gov.courrier.web.rest;

import bf.gov.courrier.CourrierApp;

import bf.gov.courrier.domain.NatureMarchand;
import bf.gov.courrier.repository.NatureMarchandRepository;
import bf.gov.courrier.service.NatureMarchandService;
import bf.gov.courrier.service.dto.NatureMarchandDTO;
import bf.gov.courrier.service.mapper.NatureMarchandMapper;
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
 * Test class for the NatureMarchandResource REST controller.
 *
 * @see NatureMarchandResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CourrierApp.class)
public class NatureMarchandResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private NatureMarchandRepository natureMarchandRepository;

    @Autowired
    private NatureMarchandMapper natureMarchandMapper;

    @Autowired
    private NatureMarchandService natureMarchandService;

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

    private MockMvc restNatureMarchandMockMvc;

    private NatureMarchand natureMarchand;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NatureMarchandResource natureMarchandResource = new NatureMarchandResource(natureMarchandService);
        this.restNatureMarchandMockMvc = MockMvcBuilders.standaloneSetup(natureMarchandResource)
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
    public static NatureMarchand createEntity(EntityManager em) {
        NatureMarchand natureMarchand = new NatureMarchand()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return natureMarchand;
    }

    @Before
    public void initTest() {
        natureMarchand = createEntity(em);
    }

    @Test
    @Transactional
    public void createNatureMarchand() throws Exception {
        int databaseSizeBeforeCreate = natureMarchandRepository.findAll().size();

        // Create the NatureMarchand
        NatureMarchandDTO natureMarchandDTO = natureMarchandMapper.toDto(natureMarchand);
        restNatureMarchandMockMvc.perform(post("/api/nature-marchands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(natureMarchandDTO)))
            .andExpect(status().isCreated());

        // Validate the NatureMarchand in the database
        List<NatureMarchand> natureMarchandList = natureMarchandRepository.findAll();
        assertThat(natureMarchandList).hasSize(databaseSizeBeforeCreate + 1);
        NatureMarchand testNatureMarchand = natureMarchandList.get(natureMarchandList.size() - 1);
        assertThat(testNatureMarchand.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testNatureMarchand.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createNatureMarchandWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = natureMarchandRepository.findAll().size();

        // Create the NatureMarchand with an existing ID
        natureMarchand.setId(1L);
        NatureMarchandDTO natureMarchandDTO = natureMarchandMapper.toDto(natureMarchand);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNatureMarchandMockMvc.perform(post("/api/nature-marchands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(natureMarchandDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NatureMarchand in the database
        List<NatureMarchand> natureMarchandList = natureMarchandRepository.findAll();
        assertThat(natureMarchandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNatureMarchands() throws Exception {
        // Initialize the database
        natureMarchandRepository.saveAndFlush(natureMarchand);

        // Get all the natureMarchandList
        restNatureMarchandMockMvc.perform(get("/api/nature-marchands?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(natureMarchand.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())));
    }
    
    @Test
    @Transactional
    public void getNatureMarchand() throws Exception {
        // Initialize the database
        natureMarchandRepository.saveAndFlush(natureMarchand);

        // Get the natureMarchand
        restNatureMarchandMockMvc.perform(get("/api/nature-marchands/{id}", natureMarchand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(natureMarchand.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNatureMarchand() throws Exception {
        // Get the natureMarchand
        restNatureMarchandMockMvc.perform(get("/api/nature-marchands/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNatureMarchand() throws Exception {
        // Initialize the database
        natureMarchandRepository.saveAndFlush(natureMarchand);

        int databaseSizeBeforeUpdate = natureMarchandRepository.findAll().size();

        // Update the natureMarchand
        NatureMarchand updatedNatureMarchand = natureMarchandRepository.findById(natureMarchand.getId()).get();
        // Disconnect from session so that the updates on updatedNatureMarchand are not directly saved in db
        em.detach(updatedNatureMarchand);
        updatedNatureMarchand
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        NatureMarchandDTO natureMarchandDTO = natureMarchandMapper.toDto(updatedNatureMarchand);

        restNatureMarchandMockMvc.perform(put("/api/nature-marchands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(natureMarchandDTO)))
            .andExpect(status().isOk());

        // Validate the NatureMarchand in the database
        List<NatureMarchand> natureMarchandList = natureMarchandRepository.findAll();
        assertThat(natureMarchandList).hasSize(databaseSizeBeforeUpdate);
        NatureMarchand testNatureMarchand = natureMarchandList.get(natureMarchandList.size() - 1);
        assertThat(testNatureMarchand.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testNatureMarchand.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingNatureMarchand() throws Exception {
        int databaseSizeBeforeUpdate = natureMarchandRepository.findAll().size();

        // Create the NatureMarchand
        NatureMarchandDTO natureMarchandDTO = natureMarchandMapper.toDto(natureMarchand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNatureMarchandMockMvc.perform(put("/api/nature-marchands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(natureMarchandDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NatureMarchand in the database
        List<NatureMarchand> natureMarchandList = natureMarchandRepository.findAll();
        assertThat(natureMarchandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNatureMarchand() throws Exception {
        // Initialize the database
        natureMarchandRepository.saveAndFlush(natureMarchand);

        int databaseSizeBeforeDelete = natureMarchandRepository.findAll().size();

        // Delete the natureMarchand
        restNatureMarchandMockMvc.perform(delete("/api/nature-marchands/{id}", natureMarchand.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NatureMarchand> natureMarchandList = natureMarchandRepository.findAll();
        assertThat(natureMarchandList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NatureMarchand.class);
        NatureMarchand natureMarchand1 = new NatureMarchand();
        natureMarchand1.setId(1L);
        NatureMarchand natureMarchand2 = new NatureMarchand();
        natureMarchand2.setId(natureMarchand1.getId());
        assertThat(natureMarchand1).isEqualTo(natureMarchand2);
        natureMarchand2.setId(2L);
        assertThat(natureMarchand1).isNotEqualTo(natureMarchand2);
        natureMarchand1.setId(null);
        assertThat(natureMarchand1).isNotEqualTo(natureMarchand2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NatureMarchandDTO.class);
        NatureMarchandDTO natureMarchandDTO1 = new NatureMarchandDTO();
        natureMarchandDTO1.setId(1L);
        NatureMarchandDTO natureMarchandDTO2 = new NatureMarchandDTO();
        assertThat(natureMarchandDTO1).isNotEqualTo(natureMarchandDTO2);
        natureMarchandDTO2.setId(natureMarchandDTO1.getId());
        assertThat(natureMarchandDTO1).isEqualTo(natureMarchandDTO2);
        natureMarchandDTO2.setId(2L);
        assertThat(natureMarchandDTO1).isNotEqualTo(natureMarchandDTO2);
        natureMarchandDTO1.setId(null);
        assertThat(natureMarchandDTO1).isNotEqualTo(natureMarchandDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(natureMarchandMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(natureMarchandMapper.fromId(null)).isNull();
    }
}
