package bf.gov.courrier.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Transitaire.
 */
@Entity
@Table(name = "transitaire")
public class Transitaire implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "email")
    private String email;

    @Column(name = "tel_1")
    private String tel1;

    @Column(name = "tel_2")
    private String tel2;

    @Column(name = "code_pays_1")
    private String codePays1;

    @Column(name = "code_pays_2")
    private String codePays2;

    @ManyToOne
    @JsonIgnoreProperties("transitaires")
    private Pays pays;
    
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

    public String getNom() {
        return nom;
    }

    public Transitaire nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public Transitaire email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel1() {
        return tel1;
    }

    public Transitaire tel1(String tel1) {
        this.tel1 = tel1;
        return this;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public Transitaire tel2(String tel2) {
        this.tel2 = tel2;
        return this;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getCodePays1() {
        return codePays1;
    }

    public Transitaire codePays1(String codePays1) {
        this.codePays1 = codePays1;
        return this;
    }

    public void setCodePays1(String codePays1) {
        this.codePays1 = codePays1;
    }

    public String getCodePays2() {
        return codePays2;
    }

    public Transitaire codePays2(String codePays2) {
        this.codePays2 = codePays2;
        return this;
    }

    public void setCodePays2(String codePays2) {
        this.codePays2 = codePays2;
    }

    public Pays getPays() {
        return pays;
    }

    public Transitaire pays(Pays pays) {
        this.pays = pays;
        return this;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
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
        Transitaire transitaire = (Transitaire) o;
        if (transitaire.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transitaire.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Transitaire{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", email='" + getEmail() + "'" +
            ", tel1='" + getTel1() + "'" +
            ", tel2='" + getTel2() + "'" +
            ", codePays1='" + getCodePays1() + "'" +
            ", codePays2='" + getCodePays2() + "'" +
            "}";
    }
}
