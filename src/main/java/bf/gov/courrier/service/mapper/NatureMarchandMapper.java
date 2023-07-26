package bf.gov.courrier.service.mapper;

import bf.gov.courrier.domain.*;
import bf.gov.courrier.service.dto.NatureMarchandDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NatureMarchand and its DTO NatureMarchandDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NatureMarchandMapper extends EntityMapper<NatureMarchandDTO, NatureMarchand> {



    default NatureMarchand fromId(Long id) {
        if (id == null) {
            return null;
        }
        NatureMarchand natureMarchand = new NatureMarchand();
        natureMarchand.setId(id);
        return natureMarchand;
    }
}
