package bf.gov.courrier.service.mapper;

import bf.gov.courrier.domain.*;
import bf.gov.courrier.service.dto.TypeTransporteurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TypeTransporteur and its DTO TypeTransporteurDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeTransporteurMapper extends EntityMapper<TypeTransporteurDTO, TypeTransporteur> {


    @Mapping(target = "fournisseurs", ignore = true)
    TypeTransporteur toEntity(TypeTransporteurDTO typeTransporteurDTO);

    default TypeTransporteur fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeTransporteur typeTransporteur = new TypeTransporteur();
        typeTransporteur.setId(id);
        return typeTransporteur;
    }
}
