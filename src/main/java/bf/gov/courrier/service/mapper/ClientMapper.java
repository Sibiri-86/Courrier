package bf.gov.courrier.service.mapper;

import bf.gov.courrier.domain.*;
import bf.gov.courrier.service.dto.ClientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Client and its DTO ClientDTO.
 */
@Mapper(componentModel = "spring", uses = {PaysMapper.class})
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {

    @Mapping(source = "pays.id", target = "paysId")
    @Mapping(source = "pays.libelle", target = "pays")
    ClientDTO toDto(Client client);

    @Mapping(source = "paysId", target = "pays")
    Client toEntity(ClientDTO clientDTO);

    default Client fromId(Long id) {
        if (id == null) {
            return null;
        }
        Client client = new Client();
        client.setId(id);
        return client;
    }
}
