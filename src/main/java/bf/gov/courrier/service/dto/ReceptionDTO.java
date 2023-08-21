package bf.gov.courrier.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Agent entity.
 */
public class ReceptionDTO implements Serializable {

    private Long id;

  
    private String numRecep;

    private String numBordereau;

    private String compagineLivraison;

    private String observation;
    
     private Long clientId;
     
     private Long fournisseurId;
     
     private Long userId;

    private String client;

    private String fournisseur;

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
        return "ReceptionDTO{" + "id=" + id + ", numRecep=" + numRecep + ", numBordereau=" + numBordereau + ", compagineLivraison=" + compagineLivraison + ", observation=" + observation + ", clientId=" + clientId + ", fournisseurId=" + fournisseurId + ", userId=" + userId + ", client=" + client + ", fournisseur=" + fournisseur + '}';
    }

   
}
