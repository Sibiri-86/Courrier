package bf.gov.courrier.service.impl;

import bf.gov.courrier.service.ProfileService;
import bf.gov.courrier.service.TailleBalleService;
import bf.gov.courrier.domain.Profile;
import bf.gov.courrier.domain.TailleBalle;
import bf.gov.courrier.repository.ProfileRepository;
import bf.gov.courrier.repository.TailleBalleRepository;
import bf.gov.courrier.service.dto.ProfileDTO;
import bf.gov.courrier.service.dto.TailleBalleDTO;
import bf.gov.courrier.service.mapper.ProfileMapper;
import bf.gov.courrier.service.mapper.TailleBalleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing TailleBalle.
 */
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

    private final Logger log = LoggerFactory.getLogger(TailleBalleServiceImpl.class);

    private final ProfileRepository profileRepository;

    private final ProfileMapper profileMapper ;

    public ProfileServiceImpl(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    /**
     * Save a tailleBalle.
     *
     * @param profileDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProfileDTO save(ProfileDTO profileDTO) {
        log.debug("Request to save TailleBalle : {}", profileDTO);
        Profile profile = profileMapper.toEntity(profileDTO);
        
        profile = profileRepository.save(profile);
        return profileMapper.toDto(profile);
    }

    /**
     * Get all the tailleBalles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProfileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TailleBalles");
        return profileRepository.findAllByDeletedFalse(pageable)
            .map(profileMapper::toDto);
    }


    /**
     * Get one tailleBalle by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProfileDTO> findOne(Long id) {
        log.debug("Request to get TailleBalle : {}", id);
        return profileRepository.findById(id)
            .map(profileMapper::toDto);
    }

    /**
     * Delete the tailleBalle by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TailleBalle : {}", id);
        profileRepository.deleteById(id);
    }
}
