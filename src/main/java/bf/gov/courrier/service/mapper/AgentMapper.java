package bf.gov.courrier.service.mapper;

import bf.gov.courrier.domain.*;
import bf.gov.courrier.service.dto.AgentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Agent and its DTO AgentDTO.
 */
@Mapper(componentModel = "spring", uses = {SiteMapper.class})
public interface AgentMapper extends EntityMapper<AgentDTO, Agent> {

    @Mapping(source = "site.id", target = "siteId")
    AgentDTO toDto(Agent agent);

    @Mapping(source = "siteId", target = "site")
    Agent toEntity(AgentDTO agentDTO);

    default Agent fromId(Long id) {
        if (id == null) {
            return null;
        }
        Agent agent = new Agent();
        agent.setId(id);
        return agent;
    }
}
