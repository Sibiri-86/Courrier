package bf.gov.courrier.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Agent.
 */
@Entity
@Table(name = "agent")
public class Agent implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "tel")
    private String tel;

    @Column(name = "code_pays")
    private String codePays;

    @Column(name = "fonction")
    private String fonction;
    
    private Long numero;

    @ManyToOne
    @JsonIgnoreProperties("agents")
    private Site site;

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

    public Agent nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Agent prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public Agent email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public Agent tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCodePays() {
        return codePays;
    }

    public Agent codePays(String codePays) {
        this.codePays = codePays;
        return this;
    }

    public void setCodePays(String codePays) {
        this.codePays = codePays;
    }

    public String getFonction() {
        return fonction;
    }

    public Agent fonction(String fonction) {
        this.fonction = fonction;
        return this;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public Site getSite() {
        return site;
    }

    public Agent site(Site site) {
        this.site = site;
        return this;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Long getNumero() {
        return numero;
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
        Agent agent = (Agent) o;
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
        return "Agent{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", email='" + getEmail() + "'" +
            ", tel='" + getTel() + "'" +
            ", codePays='" + getCodePays() + "'" +
            ", fonction='" + getFonction() + "'" +
            "}";
    }
}
