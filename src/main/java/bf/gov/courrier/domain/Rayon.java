package bf.gov.courrier.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Rayon.
 */
@Entity
@Table(name = "rayon")
public class Rayon implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero")
    private String numero;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public Rayon numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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
        Rayon rayon = (Rayon) o;
        if (rayon.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rayon.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Rayon{" +
            "id=" + getId() +
            ", numero=" + getNumero() +
            "}";
    }
}
