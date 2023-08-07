package bf.gov.courrier.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Client entity.
 */
public class ClientDTO implements Serializable {

    private Long id;

    private String nom;

    private String prenom;

    private String email;

    private String tel;

    private String codePays;

    private String codePays1;

    private String whatsapp;

    private Double longitude;

    private Double latitude;

    private String pays;

    private Long paysId;
    private Long numero;

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCodePays() {
        return codePays;
    }

    public void setCodePays(String codePays) {
        this.codePays = codePays;
    }

    public String getCodePays1() {
        return codePays1;
    }

    public void setCodePays1(String codePays1) {
        this.codePays1 = codePays1;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
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

        ClientDTO clientDTO = (ClientDTO) o;
        if (clientDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), clientDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", email='" + getEmail() + "'" +
            ", tel='" + getTel() + "'" +
            ", codePays='" + getCodePays() + "'" +
            ", codePays1='" + getCodePays1() + "'" +
            ", whatsap='" + getWhatsapp() + "'" +
            ", longitude=" + getLongitude() +
            ", latitude=" + getLatitude() +
            ", pays=" + getPaysId() +
            "}";
    }
}
