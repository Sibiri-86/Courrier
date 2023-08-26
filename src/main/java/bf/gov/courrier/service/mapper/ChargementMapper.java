package bf.gov.courrier.service.mapper;

import bf.gov.courrier.domain.Chargement;
import bf.gov.courrier.domain.Colis;
import bf.gov.courrier.domain.Emballage;
import bf.gov.courrier.domain.TailleBalle;
import bf.gov.courrier.service.dto.ChargementDTO;
import bf.gov.courrier.service.dto.EmballageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Emballage and its DTO ChargementDTO.
 */
@Mapper(componentModel = "spring", uses = {TransitaireMapper.class})
public interface ChargementMapper extends EntityMapper<ChargementDTO, Chargement>{

    @Mapping(source = "transitaire.id", target = "transitaireId")
    @Mapping(source = "transitaire.nom", target = "transitaireLibelle")    
    ChargementDTO toDto(Chargement chargement);

    @Mapping(source = "transitaireId", target = "transitaire")
    Chargement toEntity(ChargementDTO chargementDTO);

    default Chargement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Chargement chargement = new Chargement();
        chargement.setId(id);
        return chargement;
    }
}
