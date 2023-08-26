package bf.gov.courrier.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Agent.
 */
@Entity
@Table(name = "reception")
public class Reception implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_recep")
    private String numRecep;

    @Column(name = "num_bordereau")
    private String numBordereau;

    @Column(name = "compagine_livraison")
    private String compagineLivraison;

    @Column(name = "observation")
    private String observation;
    @Column(name = "date_reception")
    private LocalDate dateReception;
    @Column(name = "user_id")
     private Long userId;
   
    @ManyToOne
    @JsonIgnoreProperties("reception")
    private Fournisseur fournisseur;
    
    @ManyToOne
    @JsonIgnoreProperties("reception")
    private Client client;
    
   
    
     private Boolean deleted = Boolean.FALSE;
    @OneToMany(mappedBy = "reception")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Colis> colisList = new HashSet<>();

    public LocalDate getDateReception() {
        return dateReception;
    }

    public void setDateReception(LocalDate dateReception) {
        this.dateReception = dateReception;
    }

    
    
    public Set<Colis> getColisList() {
        return colisList;
    }

    public void setColisList(Set<Colis> colisList) {
        this.colisList = colisList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

      
   

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

    public String getNumRecep() {
        return numRecep;
    }

    public void setNumRecep(String numRecep) {
        this.numRecep = numRecep;
    }

    public String getNumBordereau() {
        return numBordereau;
    }

    public void setNumBordereau(String numBordereau) {
        this.numBordereau = numBordereau;
    }

    public String getCompagineLivraison() {
        return compagineLivraison;
    }

    public void setCompagineLivraison(String compagineLivraison) {
        this.compagineLivraison = compagineLivraison;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
