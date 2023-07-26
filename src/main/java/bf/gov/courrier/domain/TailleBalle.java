package bf.gov.courrier.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A TailleBalle.
 */
@Entity
@Table(name = "taille_balle")
public class TailleBalle implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "volume")
    private Long volume;

    @OneToMany(mappedBy = "tailleBalle")
    private Set<Tarif> tarifs = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public TailleBalle code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public TailleBalle libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getVolume() {
        return volume;
    }

    public TailleBalle volume(Long volume) {
        this.volume = volume;
        return this;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Set<Tarif> getTarifs() {
        return tarifs;
    }

    public TailleBalle tarifs(Set<Tarif> tarifs) {
        this.tarifs = tarifs;
        return this;
    }

    public TailleBalle addTarif(Tarif tarif) {
        this.tarifs.add(tarif);
        tarif.setTailleBalle(this);
        return this;
    }

    public TailleBalle removeTarif(Tarif tarif) {
        this.tarifs.remove(tarif);
        tarif.setTailleBalle(null);
        return this;
    }

    public void setTarifs(Set<Tarif> tarifs) {
        this.tarifs = tarifs;
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
        TailleBalle tailleBalle = (TailleBalle) o;
        if (tailleBalle.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tailleBalle.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TailleBalle{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", volume=" + getVolume() +
            "}";
    }
}
