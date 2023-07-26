package bf.gov.courrier.service.mapper;

import bf.gov.courrier.domain.*;
import bf.gov.courrier.service.dto.EtagereDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Etagere and its DTO EtagereDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtagereMapper extends EntityMapper<EtagereDTO, Etagere> {



    default Etagere fromId(Long id) {
        if (id == null) {
            return null;
        }
        Etagere etagere = new Etagere();
        etagere.setId(id);
        return etagere;
    }
}
