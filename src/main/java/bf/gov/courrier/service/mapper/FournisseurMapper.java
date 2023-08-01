package bf.gov.courrier.service.mapper;

import bf.gov.courrier.domain.*;
import bf.gov.courrier.service.dto.FournisseurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Fournisseur and its DTO FournisseurDTO.
 */
@Mapper(componentModel = "spring", uses = {PaysMapper.class, TypeTransporteurMapper.class})
public interface FournisseurMapper extends EntityMapper<FournisseurDTO, Fournisseur> {

    @Mapping(source = "pays.id", target = "paysId")
    @Mapping(source = "pays.libelle", target = "pays")
    @Mapping(source = "type.id", target = "typeId")
    FournisseurDTO toDto(Fournisseur fournisseur);

    @Mapping(source = "paysId", target = "pays")
    @Mapping(source = "typeId", target = "type")
    Fournisseur toEntity(FournisseurDTO fournisseurDTO);

    default Fournisseur fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(id);
        return fournisseur;
    }
}
