package bf.gov.courrier.service.mapper;

import bf.gov.courrier.domain.*;
import bf.gov.courrier.service.dto.RayonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Rayon and its DTO RayonDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RayonMapper extends EntityMapper<RayonDTO, Rayon> {



    default Rayon fromId(Long id) {
        if (id == null) {
            return null;
        }
        Rayon rayon = new Rayon();
        rayon.setId(id);
        return rayon;
    }
}
