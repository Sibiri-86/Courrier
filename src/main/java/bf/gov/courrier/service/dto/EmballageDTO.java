package bf.gov.courrier.service.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the Emballage entity.
 */
public class EmballageDTO {
    private String numBalle;
    private String taille;
    private String observation;
    private Long id;
    private Long tailleBalleId;
    private List<ColisDTO> colis = new ArrayList<>();
    private Long userId;
    private LocalDate dateEmballe;

    private Long chargementId;

    public Long getChargementId() {
        return chargementId;
    }

    public void setChargementId(Long chargementId) {
        this.chargementId = chargementId;
    }
    
    
    public LocalDate getDateEmballe() {
        return dateEmballe;
    }

    public void setDateEmballe(LocalDate dateEmballe) {
        this.dateEmballe = dateEmballe;
    }

    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    
    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
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

    public String getNumBalle() {
        return numBalle;
    }

    public void setNumBalle(String numBalle) {
        this.numBalle = numBalle;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Long getTailleBalleId() {
        return tailleBalleId;
    }

    public void setTailleBalleId(Long tailleBalleId) {
        this.tailleBalleId = tailleBalleId;
    }

   

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmballageDTO that = (EmballageDTO) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(numBalle, that.numBalle) &&
            Objects.equals(observation, that.observation) &&
            Objects.equals(tailleBalleId, that.tailleBalleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numBalle, observation, tailleBalleId);
    }

    @Override
    public String toString() {
        return "EmballageDTO{" +
            "id=" + id +
            ", numBalle='" + numBalle + '\'' +
            ", observation='" + observation + '\'' +
            ", tailleBalleId=" + tailleBalleId +
            '}';
    }
}
