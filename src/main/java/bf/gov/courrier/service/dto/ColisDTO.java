package bf.gov.courrier.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Agent.
 */

public class ColisDTO implements Serializable {

   
   

    
    private Long id;
    private String numColis;
    private String numBalle;
    private String emplacement;
    private Long volume;
    private Long poids;
    private LocalDate dateEmballe;
    private LocalDate dateRangement;
    private LocalDate dateChargement;

    private String observation;

    private Long natureId;
    
    private String nature;

    private Long receptionId;
    private String numRecep;
    
    private Long tailleId;
    private String taille;

    private Long userReceptionId;
    private String userRecep;

    private Long userEmballeId;
    private String userEmbal;

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }


    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }


    public String getUserRecep() {
        return userRecep;
    }

    public void setUserRecep(String userRecep) {
        this.userRecep = userRecep;
    }

    public String getUserEmbal() {
        return userEmbal;
    }

    public void setUserEmbal(String userEmbal) {
        this.userEmbal = userEmbal;
    }

    public String getNumRecep() {
        return numRecep;
    }

    public void setNumRecep(String numRecep) {
        this.numRecep = numRecep;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumColis() {
        return numColis;
    }

    public void setNumColis(String numColis) {
        this.numColis = numColis;
    }

    public String getNumBalle() {
        return numBalle;
    }

    public void setNumBalle(String numBalle) {
        this.numBalle = numBalle;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Long getPoids() {
        return poids;
    }

    public void setPoids(Long poids) {
        this.poids = poids;
    }

    public LocalDate getDateEmballe() {
        return dateEmballe;
    }

    public void setDateEmballe(LocalDate dateEmballe) {
        this.dateEmballe = dateEmballe;
    }

    public LocalDate getDateRangement() {
        return dateRangement;
    }

    public void setDateRangement(LocalDate dateRangement) {
        this.dateRangement = dateRangement;
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

    
    public Long getNatureId() {
        return natureId;
    }

    public void setNatureId(Long natureId) {
        this.natureId = natureId;
    }

    public Long getReceptionId() {
        return receptionId;
    }

    public void setReceptionId(Long receptionId) {
        this.receptionId = receptionId;
    }

    public Long getTailleId() {
        return tailleId;
    }

    public void setTailleId(Long tailleId) {
        this.tailleId = tailleId;
    }

    public Long getUserReceptionId() {
        return userReceptionId;
    }

    public void setUserReceptionId(Long userReceptionId) {
        this.userReceptionId = userReceptionId;
    }

    public Long getUserEmballeId() {
        return userEmballeId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove
    public void setUserEmballeId(Long userEmballeId) {
        this.userEmballeId = userEmballeId;
    }

    @Override
    public String toString() {
        return "ColisDTO{" + "id=" + id + ", numColis=" + numColis + ", numBalle=" + numBalle + ", emplacement=" + emplacement + ", volume=" + volume + ", poids=" + poids + ", dateEmballe=" + dateEmballe + ", dateRangement=" + dateRangement + ", dateChargement=" + dateChargement + ", observation=" + observation + ", natureId=" + natureId + ", receptionId=" + receptionId + ", tailleId=" + tailleId + ", userReceptionId=" + userReceptionId + ", userEmballeId=" + userEmballeId + '}';
    }

}
