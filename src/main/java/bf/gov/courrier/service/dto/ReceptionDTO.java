package bf.gov.courrier.service.dto;
import bf.gov.courrier.domain.Colis;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the Agent entity.
 */
public class ReceptionDTO implements Serializable {

    private String numRecep;
    private String client;

    private String fournisseur;
    private String compagineLivraison;
    private LocalDate dateReception;
    
    private Long id;

    private String numBordereau;


    private String observation;
    
     private Long clientId;
     
     private Long fournisseurId;
     
     private Long userId;

   
    
    private List<ColisDTO> colis = new ArrayList<>();
     private Set<Colis> colisList;

    public LocalDate getDateReception() {
        return dateReception;
    }

    public void setDateReception(LocalDate dateReception) {
        this.dateReception = dateReception;
    }

    public Set<Colis> getColisList() {
        return colisList;
    }

    public void setColisList(Set<Colis> colisList) {
        this.colisList = colisList;
    }

    public List<ColisDTO> getColis() {
        return colis;
    }

    public void setColis(List<ColisDTO> colis) {
        this.colis = colis;
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumRecep() {
        return numRecep;
    }

    public void setNumRecep(String numRecep) {
        this.numRecep = numRecep;
    }

    public String getNumBordereau() {
        return numBordereau;
    }

    public void setNumBordereau(String numBordereau) {
        this.numBordereau = numBordereau;
    }

    public String getCompagineLivraison() {
        return compagineLivraison;
    }

    public void setCompagineLivraison(String compagineLivraison) {
        this.compagineLivraison = compagineLivraison;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(Long fournisseurId) {
        this.fournisseurId = fournisseurId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }
    

  
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReceptionDTO agentDTO = (ReceptionDTO) o;
        if (agentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), agentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReceptionDTO{" + "id=" + id + ", numRecep=" + numRecep + ", numBordereau=" + numBordereau + ", compagineLivraison=" + compagineLivraison + ", observation=" + observation + ", clientId=" + clientId + ", fournisseurId=" + fournisseurId + ", userId=" + userId + ", client=" + client + ", fournisseur=" + fournisseur + ", colis=" + colis + ", colisList=" + colisList + '}';
    }

    

    

   
}
