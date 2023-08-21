package bf.gov.courrier.service.mapper;

import bf.gov.courrier.domain.*;
import bf.gov.courrier.service.dto.DroitProfileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Agent and its DTO droitProfileDTO.
 */
@Mapper(componentModel = "spring", uses = {ProfileMapper.class, MenuMapper.class})
public interface DroitProfileMapper extends EntityMapper<DroitProfileDTO, DroitProfile> {

    @Mapping(source = "menu.id", target = "menuId")
    @Mapping(source = "profile.id", target = "profileId")
    @Mapping(source = "profile.libelle", target = "profile")
    @Mapping(source = "menu.libelle", target = "menu")
            
    DroitProfileDTO toDto(DroitProfile droitProfile);

    @Mapping(source = "menuId", target = "menu")
    @Mapping(source = "profileId", target = "profile")
    DroitProfile toEntity(DroitProfileDTO droitProfileDTO);

    default DroitProfile fromId(Long id) {
        if (id == null) {
            return null;
        }
        DroitProfile droitProfile = new DroitProfile();
        droitProfile.setId(id);
        return droitProfile;
    }
}
