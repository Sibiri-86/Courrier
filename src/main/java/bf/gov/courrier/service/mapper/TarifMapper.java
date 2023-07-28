package bf.gov.courrier.service.mapper;

import bf.gov.courrier.domain.*;
import bf.gov.courrier.service.dto.TarifDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Tarif and its DTO TarifDTO.
 */
@Mapper(componentModel = "spring", uses = {TailleBalleMapper.class})
public interface TarifMapper extends EntityMapper<TarifDTO, Tarif> {

    @Mapping(source = "tailleBalle.id", target = "tailleBalleId")
    @Mapping(source = "tailleBalle.volume", target = "volume")
    TarifDTO toDto(Tarif tarif);

    @Mapping(source = "tailleBalleId", target = "tailleBalle")
    Tarif toEntity(TarifDTO tarifDTO);

    default Tarif fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tarif tarif = new Tarif();
        tarif.setId(id);
        return tarif;
    }
}
