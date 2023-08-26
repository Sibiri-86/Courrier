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
@Table(name = "emballage")
public class Emballage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "num_balle")
    private String numBalle;
    @Column(name = "observation")
    private String observation;
    @Column(name = "date_emballe")
    private LocalDate dateEmballe;

    @ManyToOne
    @JsonIgnoreProperties("emballage")
    private TailleBalle tailleBalle;
    
    @ManyToOne
    @JsonIgnoreProperties("emballage")
    private Chargement  chargement;
    
    

    private Boolean deleted = Boolean.FALSE;

    public Chargement getChargement() {
        return chargement;
    }

    public void setChargement(Chargement chargement) {
        this.chargement = chargement;
    }

    
   
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDate getDateEmballe() {
        return dateEmballe;
    }

    public void setDateEmballe(LocalDate dateEmballe) {
        this.dateEmballe = dateEmballe;
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

    public TailleBalle getTailleBalle() {
        return tailleBalle;
    }

    public void setTailleBalle(TailleBalle tailleBalle) {
        this.tailleBalle = tailleBalle;
    }

  
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emballage emballage = (Emballage) o;
        return Objects.equals(id, emballage.id) &&
            Objects.equals(numBalle, emballage.numBalle) &&
            Objects.equals(observation, emballage.observation) &&
            Objects.equals(tailleBalle, emballage.tailleBalle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numBalle, observation, tailleBalle);
    }

    @Override
    public String toString() {
        return "Emballage{" +
            "id=" + id +
            ", numBalle='" + numBalle + '\'' +
            ", observation='" + observation + '\'' +
            ", tailleBalle=" + tailleBalle +
            '}';
    }
}
