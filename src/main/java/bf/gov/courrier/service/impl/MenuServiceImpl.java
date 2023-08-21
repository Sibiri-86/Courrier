package bf.gov.courrier.service.impl;

import bf.gov.courrier.service.MenuService;
import bf.gov.courrier.service.ProfileService;
import bf.gov.courrier.service.TailleBalleService;
import bf.gov.courrier.domain.Menu;
import bf.gov.courrier.domain.Profile;
import bf.gov.courrier.domain.TailleBalle;
import bf.gov.courrier.repository.MenuRepository;
import bf.gov.courrier.repository.ProfileRepository;
import bf.gov.courrier.repository.TailleBalleRepository;
import bf.gov.courrier.service.dto.MenuDTO;
import bf.gov.courrier.service.dto.ProfileDTO;
import bf.gov.courrier.service.dto.TailleBalleDTO;
import bf.gov.courrier.service.mapper.MenuMapper;
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
public class MenuServiceImpl implements MenuService {

    private final Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);

    private final MenuRepository menuRepository;

    private final MenuMapper menuMapper ;

    public MenuServiceImpl(MenuRepository menuRepository, MenuMapper menuMapper) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }

    /**
     * Save a tailleBalle.
     *
     * @param menuDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MenuDTO save(MenuDTO menuDTO) {
        log.debug("Request to save TailleBalle : {}", menuDTO);
        Menu menu = menuMapper.toEntity(menuDTO);
        
        menu = menuRepository.save(menu);
        return menuMapper.toDto(menu);
    }

    /**
     * Get all the tailleBalles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MenuDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TailleBalles");
        return menuRepository.findAllByDeletedFalse(pageable)
            .map(menuMapper::toDto);
    }


    /**
     * Get one tailleBalle by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MenuDTO> findOne(Long id) {
        log.debug("Request to get TailleBalle : {}", id);
        return menuRepository.findById(id)
            .map(menuMapper::toDto);
    }

    /**
     * Delete the tailleBalle by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TailleBalle : {}", id);
        menuRepository.deleteById(id);
    }
}
