package bf.gov.courrier.service.mapper;

import bf.gov.courrier.domain.*;
import bf.gov.courrier.service.dto.TailleBalleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TailleBalle and its DTO TailleBalleDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TailleBalleMapper extends EntityMapper<TailleBalleDTO, TailleBalle> {


    @Mapping(target = "tarifs", ignore = true)
    TailleBalle toEntity(TailleBalleDTO tailleBalleDTO);

    default TailleBalle fromId(Long id) {
        if (id == null) {
            return null;
        }
        TailleBalle tailleBalle = new TailleBalle();
        tailleBalle.setId(id);
        return tailleBalle;
    }
}
