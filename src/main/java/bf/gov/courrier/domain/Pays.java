package bf.gov.courrier.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Pays.
 */
@Entity
@Table(name = "pays")
public class Pays implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "libelle")
    private String libelle;
     private Long numero;

    @OneToMany(mappedBy = "pays")
    private Set<Site> sites = new HashSet<>();
    @OneToMany(mappedBy = "pays")
    private Set<Transitaire> transitaires = new HashSet<>();
    @OneToMany(mappedBy = "pays")
    private Set<Fournisseur> fournisseurs = new HashSet<>();
    @OneToMany(mappedBy = "pays")
    private Set<Client> clients = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getNumero() {
        return numero;
    }

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

    public Pays code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public Pays libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Set<Site> getSites() {
        return sites;
    }

    public Pays sites(Set<Site> sites) {
        this.sites = sites;
        return this;
    }

    public Pays addSite(Site site) {
        this.sites.add(site);
        site.setPays(this);
        return this;
    }

    public Pays removeSite(Site site) {
        this.sites.remove(site);
        site.setPays(null);
        return this;
    }

    public void setSites(Set<Site> sites) {
        this.sites = sites;
    }

    public Set<Transitaire> getTransitaires() {
        return transitaires;
    }

    public Pays transitaires(Set<Transitaire> transitaires) {
        this.transitaires = transitaires;
        return this;
    }

    public Pays addTransitaire(Transitaire transitaire) {
        this.transitaires.add(transitaire);
        transitaire.setPays(this);
        return this;
    }

    public Pays removeTransitaire(Transitaire transitaire) {
        this.transitaires.remove(transitaire);
        transitaire.setPays(null);
        return this;
    }

    public void setTransitaires(Set<Transitaire> transitaires) {
        this.transitaires = transitaires;
    }

    public Set<Fournisseur> getFournisseurs() {
        return fournisseurs;
    }

    public Pays fournisseurs(Set<Fournisseur> fournisseurs) {
        this.fournisseurs = fournisseurs;
        return this;
    }

    public Pays addFournisseur(Fournisseur fournisseur) {
        this.fournisseurs.add(fournisseur);
        fournisseur.setPays(this);
        return this;
    }

    public Pays removeFournisseur(Fournisseur fournisseur) {
        this.fournisseurs.remove(fournisseur);
        fournisseur.setPays(null);
        return this;
    }

    public void setFournisseurs(Set<Fournisseur> fournisseurs) {
        this.fournisseurs = fournisseurs;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public Pays clients(Set<Client> clients) {
        this.clients = clients;
        return this;
    }

    public Pays addClient(Client client) {
        this.clients.add(client);
        client.setPays(this);
        return this;
    }

    public Pays removeClient(Client client) {
        this.clients.remove(client);
        client.setPays(null);
        return this;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
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
        Pays pays = (Pays) o;
        if (pays.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pays.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pays{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
