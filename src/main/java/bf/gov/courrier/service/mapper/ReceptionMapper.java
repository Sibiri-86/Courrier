package bf.gov.courrier.service.mapper;

import bf.gov.courrier.domain.*;
import bf.gov.courrier.service.dto.ReceptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Reception and its DTO ReceptionDTO.
 */
@Mapper(componentModel = "spring", uses = {ClientMapper.class, FournisseurMapper.class, UserMapper.class})
public interface ReceptionMapper extends EntityMapper<ReceptionDTO, Reception> {

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "client.nom", target = "client")
    @Mapping(source = "fournisseur.id", target = "fournisseurId")
    @Mapping(source = "fournisseur.nom", target = "fournisseur")
    @Mapping(source = "user.id", target = "userId")
    ReceptionDTO toDto(Reception reception);

    @Mapping(source = "clientId", target = "client")
   @Mapping(source = "fournisseurId", target = "fournisseur")
   @Mapping(source = "userId", target = "user")
    Reception toEntity(ReceptionDTO receptionDTO);

    default Reception fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reception reception = new Reception();
        reception.setId(id);
        return reception;
    }
}
