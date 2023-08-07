package bf.gov.courrier.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Site entity.
 */
public class SiteDTO implements Serializable {

    private Long id;

    private String libelle;

    private String pays;


    private Long paysId;

    private Long numero;

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

     public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getPaysId() {
        return paysId;
    }

    public void setPaysId(Long paysId) {
        this.paysId = paysId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SiteDTO siteDTO = (SiteDTO) o;
        if (siteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), siteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SiteDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", pays=" + getPaysId() +
            "}";
    }
}
