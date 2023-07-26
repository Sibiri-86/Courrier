package bf.gov.courrier.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Tarif.
 */
@Entity
@Table(name = "tarif")
public class Tarif implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "montant", precision = 10, scale = 2)
    private BigDecimal montant;

    @ManyToOne
    @JsonIgnoreProperties("tarifs")
    private TailleBalle tailleBalle;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public Tarif montant(BigDecimal montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public TailleBalle getTailleBalle() {
        return tailleBalle;
    }

    public Tarif tailleBalle(TailleBalle tailleBalle) {
        this.tailleBalle = tailleBalle;
        return this;
    }

    public void setTailleBalle(TailleBalle tailleBalle) {
        this.tailleBalle = tailleBalle;
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
        Tarif tarif = (Tarif) o;
        if (tarif.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tarif.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Tarif{" +
            "id=" + getId() +
            ", montant=" + getMontant() +
            "}";
    }
}
