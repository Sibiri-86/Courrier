package bf.gov.courrier.service.mapper;

import bf.gov.courrier.domain.*;
import bf.gov.courrier.service.dto.SiteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Site and its DTO SiteDTO.
 */
@Mapper(componentModel = "spring", uses = {PaysMapper.class})
public interface SiteMapper extends EntityMapper<SiteDTO, Site> {

    @Mapping(source = "pays.id", target = "paysId")
    SiteDTO toDto(Site site);

    @Mapping(target = "agents", ignore = true)
    @Mapping(source = "paysId", target = "pays")
    Site toEntity(SiteDTO siteDTO);

    default Site fromId(Long id) {
        if (id == null) {
            return null;
        }
        Site site = new Site();
        site.setId(id);
        return site;
    }
}
