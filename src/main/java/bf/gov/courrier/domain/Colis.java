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

    @Column(name = "observation")
    private String observation;


    @ManyToOne
    @JsonIgnoreProperties("colis")
    private NatureMarchand nature;

    @ManyToOne
    @JsonIgnoreProperties("colis")
    private Reception reception;

    @ManyToOne
    @JsonIgnoreProperties("colis")
    private TailleBalle taille;

    @ManyToOne
    @JsonIgnoreProperties("colis")
    private User userReception;

    @ManyToOne
    @JsonIgnoreProperties("colis")
    private User userEmballe;

    private Boolean deleted = Boolean.FALSE;

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

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
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

    public User getUserReception() {
        return userReception;
    }

    public void setUserReception(User userReception) {
        this.userReception = userReception;
    }

    public User getUserEmballe() {
        return userEmballe;
    }

    public void setUserEmballe(User userEmballe) {
        this.userEmballe = userEmballe;
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
        return "Reception{" +
            "id=" + getId() +

            "}";
    }
}
