package bf.gov.courrier.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Emballage.
 */
@Entity
@Table(name = "chargement")
public class Chargement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "num_balle")
    private String numBalle;
    @Column(name = "num_logique_container")
    private String numLogiqueContainer;
    @Column(name = "num_physique_container")
    private String numPhysiqueContainer;
    @Column(name = "num_groupage")
    private String numGroupage;
    @Column(name = "poids")
    private String poids;
   
    @Column(name = "date_chargement")
    private LocalDate dateChargement;
    @Column(name = "observation")
    private String observation;
    @ManyToOne
    @JsonIgnoreProperties("chargement")
    private Transitaire transitaire;

    private Boolean deleted = Boolean.FALSE;

   
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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

    public Transitaire getTransitaire() {
        return transitaire;
    }

    public void setTransitaire(Transitaire transitaire) {
        this.transitaire = transitaire;
    }

    
}
