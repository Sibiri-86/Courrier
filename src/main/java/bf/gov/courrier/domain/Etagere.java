package bf.gov.courrier.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Etagere.
 */
@Entity
@Table(name = "etagere")
public class Etagere implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero")
    private Long numero;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public Etagere numero(Long numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Long numero) {
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
        Etagere etagere = (Etagere) o;
        if (etagere.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etagere.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Etagere{" +
            "id=" + getId() +
            ", numero=" + getNumero() +
            "}";
    }
}
