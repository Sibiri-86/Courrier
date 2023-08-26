package bf.gov.courrier.service.mapper;

import bf.gov.courrier.domain.Colis;
import bf.gov.courrier.domain.Emballage;
import bf.gov.courrier.domain.TailleBalle;
import bf.gov.courrier.service.dto.EmballageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Emballage and its DTO EmballageDTO.
 */
@Mapper(componentModel = "spring", uses = {TailleBalleMapper.class, ChargementMapper.class})
public interface EmballageMapper extends EntityMapper<EmballageDTO, Emballage>{

    @Mapping(source = "tailleBalle.id", target = "tailleBalleId")
    @Mapping(source = "tailleBalle.libelle", target = "taille")    
    @Mapping(source = "chargement.id", target = "chargementId")    
    EmballageDTO toDto(Emballage emballage);

    @Mapping(source = "tailleBalleId", target = "tailleBalle")
    @Mapping(source = "chargementId", target = "chargement")
    Emballage toEntity(EmballageDTO emballageDTO);

    default Emballage fromId(Long id) {
        if (id == null) {
            return null;
        }
        Emballage emballage = new Emballage();
        emballage.setId(id);
        return emballage;
    }
}
