package bf.gov.courrier.web.rest;

import bf.gov.courrier.CourrierApp;

import bf.gov.courrier.domain.TypeTransporteur;
import bf.gov.courrier.repository.TypeTransporteurRepository;
import bf.gov.courrier.service.TypeTransporteurService;
import bf.gov.courrier.service.dto.TypeTransporteurDTO;
import bf.gov.courrier.service.mapper.TypeTransporteurMapper;
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
 * Test class for the TypeTransporteurResource REST controller.
 *
 * @see TypeTransporteurResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CourrierApp.class)
public class TypeTransporteurResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    @Autowired
    private TypeTransporteurRepository typeTransporteurRepository;

    @Autowired
    private TypeTransporteurMapper typeTransporteurMapper;

    @Autowired
    private TypeTransporteurService typeTransporteurService;

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

    private MockMvc restTypeTransporteurMockMvc;

    private TypeTransporteur typeTransporteur;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TypeTransporteurResource typeTransporteurResource = new TypeTransporteurResource(typeTransporteurService);
        this.restTypeTransporteurMockMvc = MockMvcBuilders.standaloneSetup(typeTransporteurResource)
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
    public static TypeTransporteur createEntity(EntityManager em) {
        TypeTransporteur typeTransporteur = new TypeTransporteur()
            .code(DEFAULT_CODE)
            .libelle(DEFAULT_LIBELLE);
        return typeTransporteur;
    }

    @Before
    public void initTest() {
        typeTransporteur = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeTransporteur() throws Exception {
        int databaseSizeBeforeCreate = typeTransporteurRepository.findAll().size();

        // Create the TypeTransporteur
        TypeTransporteurDTO typeTransporteurDTO = typeTransporteurMapper.toDto(typeTransporteur);
        restTypeTransporteurMockMvc.perform(post("/api/type-transporteurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeTransporteurDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeTransporteur in the database
        List<TypeTransporteur> typeTransporteurList = typeTransporteurRepository.findAll();
        assertThat(typeTransporteurList).hasSize(databaseSizeBeforeCreate + 1);
        TypeTransporteur testTypeTransporteur = typeTransporteurList.get(typeTransporteurList.size() - 1);
        assertThat(testTypeTransporteur.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testTypeTransporteur.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
    }

    @Test
    @Transactional
    public void createTypeTransporteurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeTransporteurRepository.findAll().size();

        // Create the TypeTransporteur with an existing ID
        typeTransporteur.setId(1L);
        TypeTransporteurDTO typeTransporteurDTO = typeTransporteurMapper.toDto(typeTransporteur);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeTransporteurMockMvc.perform(post("/api/type-transporteurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeTransporteurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeTransporteur in the database
        List<TypeTransporteur> typeTransporteurList = typeTransporteurRepository.findAll();
        assertThat(typeTransporteurList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTypeTransporteurs() throws Exception {
        // Initialize the database
        typeTransporteurRepository.saveAndFlush(typeTransporteur);

        // Get all the typeTransporteurList
        restTypeTransporteurMockMvc.perform(get("/api/type-transporteurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeTransporteur.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())));
    }
    
    @Test
    @Transactional
    public void getTypeTransporteur() throws Exception {
        // Initialize the database
        typeTransporteurRepository.saveAndFlush(typeTransporteur);

        // Get the typeTransporteur
        restTypeTransporteurMockMvc.perform(get("/api/type-transporteurs/{id}", typeTransporteur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(typeTransporteur.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTypeTransporteur() throws Exception {
        // Get the typeTransporteur
        restTypeTransporteurMockMvc.perform(get("/api/type-transporteurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeTransporteur() throws Exception {
        // Initialize the database
        typeTransporteurRepository.saveAndFlush(typeTransporteur);

        int databaseSizeBeforeUpdate = typeTransporteurRepository.findAll().size();

        // Update the typeTransporteur
        TypeTransporteur updatedTypeTransporteur = typeTransporteurRepository.findById(typeTransporteur.getId()).get();
        // Disconnect from session so that the updates on updatedTypeTransporteur are not directly saved in db
        em.detach(updatedTypeTransporteur);
        updatedTypeTransporteur
            .code(UPDATED_CODE)
            .libelle(UPDATED_LIBELLE);
        TypeTransporteurDTO typeTransporteurDTO = typeTransporteurMapper.toDto(updatedTypeTransporteur);

        restTypeTransporteurMockMvc.perform(put("/api/type-transporteurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeTransporteurDTO)))
            .andExpect(status().isOk());

        // Validate the TypeTransporteur in the database
        List<TypeTransporteur> typeTransporteurList = typeTransporteurRepository.findAll();
        assertThat(typeTransporteurList).hasSize(databaseSizeBeforeUpdate);
        TypeTransporteur testTypeTransporteur = typeTransporteurList.get(typeTransporteurList.size() - 1);
        assertThat(testTypeTransporteur.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testTypeTransporteur.getLibelle()).isEqualTo(UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeTransporteur() throws Exception {
        int databaseSizeBeforeUpdate = typeTransporteurRepository.findAll().size();

        // Create the TypeTransporteur
        TypeTransporteurDTO typeTransporteurDTO = typeTransporteurMapper.toDto(typeTransporteur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeTransporteurMockMvc.perform(put("/api/type-transporteurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeTransporteurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeTransporteur in the database
        List<TypeTransporteur> typeTransporteurList = typeTransporteurRepository.findAll();
        assertThat(typeTransporteurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeTransporteur() throws Exception {
        // Initialize the database
        typeTransporteurRepository.saveAndFlush(typeTransporteur);

        int databaseSizeBeforeDelete = typeTransporteurRepository.findAll().size();

        // Delete the typeTransporteur
        restTypeTransporteurMockMvc.perform(delete("/api/type-transporteurs/{id}", typeTransporteur.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TypeTransporteur> typeTransporteurList = typeTransporteurRepository.findAll();
        assertThat(typeTransporteurList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeTransporteur.class);
        TypeTransporteur typeTransporteur1 = new TypeTransporteur();
        typeTransporteur1.setId(1L);
        TypeTransporteur typeTransporteur2 = new TypeTransporteur();
        typeTransporteur2.setId(typeTransporteur1.getId());
        assertThat(typeTransporteur1).isEqualTo(typeTransporteur2);
        typeTransporteur2.setId(2L);
        assertThat(typeTransporteur1).isNotEqualTo(typeTransporteur2);
        typeTransporteur1.setId(null);
        assertThat(typeTransporteur1).isNotEqualTo(typeTransporteur2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeTransporteurDTO.class);
        TypeTransporteurDTO typeTransporteurDTO1 = new TypeTransporteurDTO();
        typeTransporteurDTO1.setId(1L);
        TypeTransporteurDTO typeTransporteurDTO2 = new TypeTransporteurDTO();
        assertThat(typeTransporteurDTO1).isNotEqualTo(typeTransporteurDTO2);
        typeTransporteurDTO2.setId(typeTransporteurDTO1.getId());
        assertThat(typeTransporteurDTO1).isEqualTo(typeTransporteurDTO2);
        typeTransporteurDTO2.setId(2L);
        assertThat(typeTransporteurDTO1).isNotEqualTo(typeTransporteurDTO2);
        typeTransporteurDTO1.setId(null);
        assertThat(typeTransporteurDTO1).isNotEqualTo(typeTransporteurDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(typeTransporteurMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(typeTransporteurMapper.fromId(null)).isNull();
    }
}
