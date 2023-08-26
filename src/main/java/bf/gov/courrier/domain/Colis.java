package bf.gov.courrier.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Agent.
 */
@Entity
@Table(name = "colis")
public class Colis implements Serializable {

    private static final long serialVersionUID = 1L;
   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_colis")
    private String numColis;

    @Column(name = "num_balle")
    private String numBalle;

    @Column(name = "emplacement")
    private String emplacement;

    @Column(name = "volume")
    private Long volume;
    @Column(name = "poids")
    private Long poids;

    @Column(name = "date_emballe")
    private LocalDate dateEmballe;
    @Column(name = "date_rangement")
    private LocalDate dateRangement;
    @Column(name = "date_chargement")
    private LocalDate dateChargement;
    @Column(name = "date_reception")
    private LocalDate dateReception;
    
   @Column(name = "statut")
    private Statut statut;
   @Column(name = "user_chargement_id")
    private Long userChargementId;
   @Column(name = "user_reception_id")
    private Long userReceptionId;
    @Column(name = "user_emballe_id")
    private Long userEmballe_Id;
    


    @ManyToOne
    @JsonIgnoreProperties("colis")
    private NatureMarchand nature;

    @ManyToOne
    @JsonIgnoreProperties("colisList")
    private Reception reception;
    
    @ManyToOne
    @JsonIgnoreProperties("colis")
    private TailleBalle taille;

    
    @ManyToOne
    @JsonIgnoreProperties("colis")
    private Emballage emballage;

    private Boolean deleted = Boolean.FALSE;

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

    public Long getUserReceptionId() {
        return userReceptionId;
    }

    public void setUserReceptionId(Long userReceptionId) {
        this.userReceptionId = userReceptionId;
    }

    public Long getUserEmballe_Id() {
        return userEmballe_Id;
    }

    public void setUserEmballe_Id(Long userEmballe_Id) {
        this.userEmballe_Id = userEmballe_Id;
    }

    
    public Emballage getEmballage() {
        return emballage;
    }

    public void setEmballage(Emballage emballage) {
        this.emballage = emballage;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    
    
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }



    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
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

    

    public NatureMarchand getNature() {
        return nature;
    }

    public void setNature(NatureMarchand nature) {
        this.nature = nature;
    }

    public Reception getReception() {
        return reception;
    }

    public void setReception(Reception reception) {
        this.reception = reception;
    }

    public TailleBalle getTaille() {
        return taille;
    }

    public void setTaille(TailleBalle taille) {
        this.taille = taille;
    }

   

   



    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reception agent = (Reception) o;
        if (agent.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), agent.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Colis{" + "id=" + id + ", numColis=" + numColis + ", numBalle=" + numBalle + ", emplacement=" + emplacement + ", volume=" + volume + ", poids=" + poids + ", dateEmballe=" + dateEmballe + ", dateRangement=" + dateRangement + ", dateChargement=" + dateChargement + ", nature=" + nature + ", reception=" + reception + ", taille=" + taille +", deleted=" + deleted + '}';
    }

    
}
