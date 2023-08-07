package bf.gov.courrier.domain;



import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A NatureMarchand.
 */
@Entity
@Table(name = "nature_marchand")
public class NatureMarchand implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "libelle")
    private String libelle;
    
     private Long numero;
     
    public Long getNumero() {
        return numero;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public void setNumero(Long numero) {     
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public NatureMarchand code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public NatureMarchand libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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
        NatureMarchand natureMarchand = (NatureMarchand) o;
        if (natureMarchand.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), natureMarchand.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NatureMarchand{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
