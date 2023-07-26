package bf.gov.courrier.service.mapper;

import bf.gov.courrier.domain.*;
import bf.gov.courrier.service.dto.TransporteurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Transporteur and its DTO TransporteurDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TransporteurMapper extends EntityMapper<TransporteurDTO, Transporteur> {



    default Transporteur fromId(Long id) {
        if (id == null) {
            return null;
        }
        Transporteur transporteur = new Transporteur();
        transporteur.setId(id);
        return transporteur;
    }
}
