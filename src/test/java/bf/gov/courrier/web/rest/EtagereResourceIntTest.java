package bf.gov.courrier.web.rest;

import bf.gov.courrier.CourrierApp;

import bf.gov.courrier.domain.Etagere;
import bf.gov.courrier.repository.EtagereRepository;
import bf.gov.courrier.service.EtagereService;
import bf.gov.courrier.service.dto.EtagereDTO;
import bf.gov.courrier.service.mapper.EtagereMapper;
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
 * Test class for the EtagereResource REST controller.
 *
 * @see EtagereResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CourrierApp.class)
public class EtagereResourceIntTest {

    private static final Long DEFAULT_NUMERO = 1L;
    private static final Long UPDATED_NUMERO = 2L;

    @Autowired
    private EtagereRepository etagereRepository;

    @Autowired
    private EtagereMapper etagereMapper;

    @Autowired
    private EtagereService etagereService;

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

    private MockMvc restEtagereMockMvc;

    private Etagere etagere;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EtagereResource etagereResource = new EtagereResource(etagereService);
        this.restEtagereMockMvc = MockMvcBuilders.standaloneSetup(etagereResource)
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
    public static Etagere createEntity(EntityManager em) {
        Etagere etagere = new Etagere()
            .numero(DEFAULT_NUMERO);
        return etagere;
    }

    @Before
    public void initTest() {
        etagere = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtagere() throws Exception {
        int databaseSizeBeforeCreate = etagereRepository.findAll().size();

        // Create the Etagere
        EtagereDTO etagereDTO = etagereMapper.toDto(etagere);
        restEtagereMockMvc.perform(post("/api/etageres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etagereDTO)))
            .andExpect(status().isCreated());

        // Validate the Etagere in the database
        List<Etagere> etagereList = etagereRepository.findAll();
        assertThat(etagereList).hasSize(databaseSizeBeforeCreate + 1);
        Etagere testEtagere = etagereList.get(etagereList.size() - 1);
        assertThat(testEtagere.getNumero()).isEqualTo(DEFAULT_NUMERO);
    }

    @Test
    @Transactional
    public void createEtagereWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = etagereRepository.findAll().size();

        // Create the Etagere with an existing ID
        etagere.setId(1L);
        EtagereDTO etagereDTO = etagereMapper.toDto(etagere);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEtagereMockMvc.perform(post("/api/etageres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etagereDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Etagere in the database
        List<Etagere> etagereList = etagereRepository.findAll();
        assertThat(etagereList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEtageres() throws Exception {
        // Initialize the database
        etagereRepository.saveAndFlush(etagere);

        // Get all the etagereList
        restEtagereMockMvc.perform(get("/api/etageres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(etagere.getId().intValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.intValue())));
    }
    
    @Test
    @Transactional
    public void getEtagere() throws Exception {
        // Initialize the database
        etagereRepository.saveAndFlush(etagere);

        // Get the etagere
        restEtagereMockMvc.perform(get("/api/etageres/{id}", etagere.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(etagere.getId().intValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEtagere() throws Exception {
        // Get the etagere
        restEtagereMockMvc.perform(get("/api/etageres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtagere() throws Exception {
        // Initialize the database
        etagereRepository.saveAndFlush(etagere);

        int databaseSizeBeforeUpdate = etagereRepository.findAll().size();

        // Update the etagere
        Etagere updatedEtagere = etagereRepository.findById(etagere.getId()).get();
        // Disconnect from session so that the updates on updatedEtagere are not directly saved in db
        em.detach(updatedEtagere);
        updatedEtagere
            .numero(UPDATED_NUMERO);
        EtagereDTO etagereDTO = etagereMapper.toDto(updatedEtagere);

        restEtagereMockMvc.perform(put("/api/etageres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etagereDTO)))
            .andExpect(status().isOk());

        // Validate the Etagere in the database
        List<Etagere> etagereList = etagereRepository.findAll();
        assertThat(etagereList).hasSize(databaseSizeBeforeUpdate);
        Etagere testEtagere = etagereList.get(etagereList.size() - 1);
        assertThat(testEtagere.getNumero()).isEqualTo(UPDATED_NUMERO);
    }

    @Test
    @Transactional
    public void updateNonExistingEtagere() throws Exception {
        int databaseSizeBeforeUpdate = etagereRepository.findAll().size();

        // Create the Etagere
        EtagereDTO etagereDTO = etagereMapper.toDto(etagere);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEtagereMockMvc.perform(put("/api/etageres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(etagereDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Etagere in the database
        List<Etagere> etagereList = etagereRepository.findAll();
        assertThat(etagereList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEtagere() throws Exception {
        // Initialize the database
        etagereRepository.saveAndFlush(etagere);

        int databaseSizeBeforeDelete = etagereRepository.findAll().size();

        // Delete the etagere
        restEtagereMockMvc.perform(delete("/api/etageres/{id}", etagere.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Etagere> etagereList = etagereRepository.findAll();
        assertThat(etagereList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Etagere.class);
        Etagere etagere1 = new Etagere();
        etagere1.setId(1L);
        Etagere etagere2 = new Etagere();
        etagere2.setId(etagere1.getId());
        assertThat(etagere1).isEqualTo(etagere2);
        etagere2.setId(2L);
        assertThat(etagere1).isNotEqualTo(etagere2);
        etagere1.setId(null);
        assertThat(etagere1).isNotEqualTo(etagere2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EtagereDTO.class);
        EtagereDTO etagereDTO1 = new EtagereDTO();
        etagereDTO1.setId(1L);
        EtagereDTO etagereDTO2 = new EtagereDTO();
        assertThat(etagereDTO1).isNotEqualTo(etagereDTO2);
        etagereDTO2.setId(etagereDTO1.getId());
        assertThat(etagereDTO1).isEqualTo(etagereDTO2);
        etagereDTO2.setId(2L);
        assertThat(etagereDTO1).isNotEqualTo(etagereDTO2);
        etagereDTO1.setId(null);
        assertThat(etagereDTO1).isNotEqualTo(etagereDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(etagereMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(etagereMapper.fromId(null)).isNull();
    }
}
