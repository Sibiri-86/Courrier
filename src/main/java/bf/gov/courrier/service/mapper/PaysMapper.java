package bf.gov.courrier.service.mapper;

import bf.gov.courrier.domain.*;
import bf.gov.courrier.service.dto.PaysDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Pays and its DTO PaysDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PaysMapper extends EntityMapper<PaysDTO, Pays> {


    @Mapping(target = "sites", ignore = true)
    @Mapping(target = "transitaires", ignore = true)
    @Mapping(target = "fournisseurs", ignore = true)
    @Mapping(target = "clients", ignore = true)
    Pays toEntity(PaysDTO paysDTO);

    default Pays fromId(Long id) {
        if (id == null) {
            return null;
        }
        Pays pays = new Pays();
        pays.setId(id);
        return pays;
    }
}
