package bf.gov.courrier.service.dto;
import bf.gov.courrier.domain.Colis;
import bf.gov.courrier.domain.User;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;

/**
 * A DTO for the Agent entity.
 */
public class ChargementDTO implements Serializable {
    private String numBalle;
    private String numPhysiqueContainer;
    private String numLogiqueContainer;
    private String numGroupage;
    private String poids;
    private String transitaireLibelle;
     private Long id;
    private Long transitaireId;
   
    private LocalDate dateChargement;
    private String observation;
    
    private List<EmballageDTO> emballages = new ArrayList<>();
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

   

    public Long getTransitaireId() {
        return transitaireId;
    }

    public void setTransitaireId(Long transitaireId) {
        this.transitaireId = transitaireId;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumBalle() {
        return numBalle;
    }

    public void setNumBalle(String numBalle) {
        this.numBalle = numBalle;
    }

    public String getNumLogiqueContainer() {
        return numLogiqueContainer;
    }

    public void setNumLogiqueContainer(String numLogiqueContainer) {
        this.numLogiqueContainer = numLogiqueContainer;
    }

    public String getNumPhysiqueContainer() {
        return numPhysiqueContainer;
    }

    public void setNumPhysiqueContainer(String numPhysiqueContainer) {
        this.numPhysiqueContainer = numPhysiqueContainer;
    }

    public String getNumGroupage() {
        return numGroupage;
    }

    public void setNumGroupage(String numGroupage) {
        this.numGroupage = numGroupage;
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public LocalDate getDateChargement() {
        return dateChargement;
    }

    public void setDateChargement(LocalDate dateChargement) {
        this.dateChargement = dateChargement;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getTransitaireLibelle() {
        return transitaireLibelle;
    }

    public void setTransitaireLibelle(String transitaireLibelle) {
        this.transitaireLibelle = transitaireLibelle;
    }

  

    public List<EmballageDTO> getEmballages() {
        return emballages;
    }

    public void setEmballages(List<EmballageDTO> emballages) {
        this.emballages = emballages;
    }

    @Override
    public String toString() {
        return "ChargementDTO{" + "id=" + id + ", numBalle=" + numBalle + ", numLogiqueContainer=" + numLogiqueContainer + ", numPhysiqueContainer=" + numPhysiqueContainer + ", numGroupage=" + numGroupage + ", poids=" + poids + ", dateChargement=" + dateChargement + ", observation=" + observation + ", transitaireLibelle=" + transitaireLibelle + ", emballages=" + emballages + '}';
    }

   

   
  

    

    

   
}
