package bf.gov.courrier.service.mapper;

import bf.gov.courrier.domain.*;
import bf.gov.courrier.service.dto.TransitaireDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Transitaire and its DTO TransitaireDTO.
 */
@Mapper(componentModel = "spring", uses = {PaysMapper.class})
public interface TransitaireMapper extends EntityMapper<TransitaireDTO, Transitaire> {

    @Mapping(source = "pays.id", target = "paysId")
    @Mapping(source = "pays.libelle", target = "pays")
    TransitaireDTO toDto(Transitaire transitaire);

    @Mapping(source = "paysId", target = "pays")
    Transitaire toEntity(TransitaireDTO transitaireDTO);

    default Transitaire fromId(Long id) {
        if (id == null) {
            return null;
        }
        Transitaire transitaire = new Transitaire();
        transitaire.setId(id);
        return transitaire;
    }
}
