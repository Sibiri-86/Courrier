package bf.gov.courrier.service.mapper;

import bf.gov.courrier.domain.*;
import bf.gov.courrier.service.dto.ColisDTO;
import bf.gov.courrier.service.dto.ReceptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Colis and its DTO ColisDTO.
 */
@Mapper(componentModel = "spring", uses = {NatureMarchandMapper.class, TailleBalleMapper.class, ReceptionMapper.class,
EmballageMapper.class})
public interface ColisMapper extends EntityMapper<ColisDTO, Colis> {

    @Mapping(source = "nature.id", target = "natureId")
    @Mapping(source = "nature.libelle", target = "nature")
    @Mapping(source = "taille.id", target = "tailleId")
    @Mapping(source = "taille.libelle", target = "taille")
    @Mapping(source = "reception.id", target = "receptionId")
    @Mapping(source = "reception.numRecep", target = "recep")
    @Mapping(source = "emballage.id", target = "emballageId")
   
    
    ColisDTO toDto(Colis colis);

   @Mapping(source = "receptionId", target = "reception")
   @Mapping(source = "natureId", target = "nature")
   @Mapping(source = "tailleId", target = "taille")
    Colis toEntity(ColisDTO colisDTO);

    default Colis fromId(Long id) {
        if (id == null) {
            return null;
        }
        Colis colis = new Colis();
        colis.setId(id);
        return colis;
    }
}
