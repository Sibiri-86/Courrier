package bf.gov.courrier.web.rest;

import bf.gov.courrier.CourrierApp;

import bf.gov.courrier.domain.Transporteur;
import bf.gov.courrier.repository.TransporteurRepository;
import bf.gov.courrier.service.TransporteurService;
import bf.gov.courrier.service.dto.TransporteurDTO;
import bf.gov.courrier.service.mapper.TransporteurMapper;
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
 * Test class for the TransporteurResource REST controller.
 *
 * @see TransporteurResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CourrierApp.class)
public class TransporteurResourceIntTest {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private TransporteurRepository transporteurRepository;

    @Autowired
    private TransporteurMapper transporteurMapper;

    @Autowired
    private TransporteurService transporteurService;

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

    private MockMvc restTransporteurMockMvc;

    private Transporteur transporteur;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TransporteurResource transporteurResource = new TransporteurResource(transporteurService);
        this.restTransporteurMockMvc = MockMvcBuilders.standaloneSetup(transporteurResource)
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
    public static Transporteur createEntity(EntityManager em) {
        Transporteur transporteur = new Transporteur()
            .libelle(DEFAULT_LIBELLE);
        return transporteur;
    }

    @Before
    public void initTest() {
        transporteur = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransporteur() throws Exception {
        int databaseSizeBeforeCreate = transporteurRepository.findAll().size();

        // Create the Transporteur
        TransporteurDTO transporteurDTO = transporteurMapper.toDto(transporteur);
        restTransporteurMockMvc.perform(post("/api/transporteurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transporteurDTO)))
            .andExpect(status().isCreated());

        // Validate the Transporteur in the database
        List<Transporteur> transporteurList = transporteurRepository.findAll();
        assertThat(transporteurList).hasSize(databaseSizeBeforeCreate + 1);
        Transporteur testTransporteur = transporteurList.get(transporteurList.size() - 1);
        assertThat(testTransporteur.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createTransporteurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transporteurRepository.findAll().size();

        // Create the Transporteur with an existing ID
        transporteur.setId(1L);
        TransporteurDTO transporteurDTO = transporteurMapper.toDto(transporteur);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransporteurMockMvc.perform(post("/api/transporteurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transporteurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Transporteur in the database
        List<Transporteur> transporteurList = transporteurRepository.findAll();
        assertThat(transporteurList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTransporteurs() throws Exception {
        // Initialize the database
        transporteurRepository.saveAndFlush(transporteur);

        // Get all the transporteurList
        restTransporteurMockMvc.perform(get("/api/transporteurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transporteur.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())));
    }
    
    @Test
    @Transactional
    public void getTransporteur() throws Exception {
        // Initialize the database
        transporteurRepository.saveAndFlush(transporteur);

        // Get the transporteur
        restTransporteurMockMvc.perform(get("/api/transporteurs/{id}", transporteur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(transporteur.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTransporteur() throws Exception {
        // Get the transporteur
        restTransporteurMockMvc.perform(get("/api/transporteurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransporteur() throws Exception {
        // Initialize the database
        transporteurRepository.saveAndFlush(transporteur);

        int databaseSizeBeforeUpdate = transporteurRepository.findAll().size();

        // Update the transporteur
        Transporteur updatedTransporteur = transporteurRepository.findById(transporteur.getId()).get();
        // Disconnect from session so that the updates on updatedTransporteur are not directly saved in db
        em.detach(updatedTransporteur);
        updatedTransporteur
            .libelle(UPDATED_LIBELLE);
        TransporteurDTO transporteurDTO = transporteurMapper.toDto(updatedTransporteur);

        restTransporteurMockMvc.perform(put("/api/transporteurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transporteurDTO)))
            .andExpect(status().isOk());

        // Validate the Transporteur in the database
        List<Transporteur> transporteurList = transporteurRepository.findAll();
        assertThat(transporteurList).hasSize(databaseSizeBeforeUpdate);
        Transporteur testTransporteur = transporteurList.get(transporteurList.size() - 1);
        assertThat(testTransporteur.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingTransporteur() throws Exception {
        int databaseSizeBeforeUpdate = transporteurRepository.findAll().size();

        // Create the Transporteur
        TransporteurDTO transporteurDTO = transporteurMapper.toDto(transporteur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransporteurMockMvc.perform(put("/api/transporteurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(transporteurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Transporteur in the database
        List<Transporteur> transporteurList = transporteurRepository.findAll();
        assertThat(transporteurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransporteur() throws Exception {
        // Initialize the database
        transporteurRepository.saveAndFlush(transporteur);

        int databaseSizeBeforeDelete = transporteurRepository.findAll().size();

        // Delete the transporteur
        restTransporteurMockMvc.perform(delete("/api/transporteurs/{id}", transporteur.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Transporteur> transporteurList = transporteurRepository.findAll();
        assertThat(transporteurList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Transporteur.class);
        Transporteur transporteur1 = new Transporteur();
        transporteur1.setId(1L);
        Transporteur transporteur2 = new Transporteur();
        transporteur2.setId(transporteur1.getId());
        assertThat(transporteur1).isEqualTo(transporteur2);
        transporteur2.setId(2L);
        assertThat(transporteur1).isNotEqualTo(transporteur2);
        transporteur1.setId(null);
        assertThat(transporteur1).isNotEqualTo(transporteur2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransporteurDTO.class);
        TransporteurDTO transporteurDTO1 = new TransporteurDTO();
        transporteurDTO1.setId(1L);
        TransporteurDTO transporteurDTO2 = new TransporteurDTO();
        assertThat(transporteurDTO1).isNotEqualTo(transporteurDTO2);
        transporteurDTO2.setId(transporteurDTO1.getId());
        assertThat(transporteurDTO1).isEqualTo(transporteurDTO2);
        transporteurDTO2.setId(2L);
        assertThat(transporteurDTO1).isNotEqualTo(transporteurDTO2);
        transporteurDTO1.setId(null);
        assertThat(transporteurDTO1).isNotEqualTo(transporteurDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(transporteurMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(transporteurMapper.fromId(null)).isNull();
    }
}
