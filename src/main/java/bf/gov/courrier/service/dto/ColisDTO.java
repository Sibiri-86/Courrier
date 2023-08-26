package bf.gov.courrier.service.dto;




import bf.gov.courrier.domain.Statut;
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
    private LocalDate dateReception;

    private String observation;

    private Long natureId;
    
    private String nature;

    private Long receptionId;
    private Long userChargementId;
    private String recep;
    
    private Long tailleId;
    private String taille;

    private Long userReceptionId;
    private String userRecep;
    
    private Long userEmballeId;
    private String userEmb;
    private Long emballageId;
    private Statut statut;

    public LocalDate getDateReception() {
        return dateReception;
    }

    public void setDateReception(LocalDate dateReception) {
        this.dateReception = dateReception;
    }

    
    public Long getUserChargementId() {
        return userChargementId;
    }

    public void setUserChargementId(Long userChargementId) {
        this.userChargementId = userChargementId;
    }

    
    public Statut getStatut() {
        return statut;
    }

    
    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    
    public Long getEmballageId() {
        return emballageId;
    }

    public void setEmballageId(Long emballageId) {
        this.emballageId = emballageId;
    }

    
    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getRecep() {
        return recep;
    }

    public void setRecep(String recep) {
        this.recep = recep;
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

    public String getUserEmb() {
        return userEmb;
    }

    public void setUserEmb(String userEmb) {
        this.userEmb = userEmb;
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
