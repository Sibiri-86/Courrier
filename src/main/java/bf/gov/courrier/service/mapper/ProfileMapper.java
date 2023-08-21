package bf.gov.courrier.service.mapper;

import bf.gov.courrier.domain.*;
import bf.gov.courrier.service.dto.ProfileDTO;
import bf.gov.courrier.service.dto.TailleBalleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TailleBalle and its DTO TailleBalleDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProfileMapper extends EntityMapper<ProfileDTO, Profile> {


   // @Mapping(target = "tarifs", ignore = true)
    Profile toEntity(ProfileDTO profileDTO);

    default Profile fromId(Long id) {
        if (id == null) {
            return null;
        }
        Profile profile = new Profile();
        profile.setId(id);
        return profile;
    }
}
