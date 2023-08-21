package bf.gov.courrier.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Transitaire entity.
 */
public class TransitaireDTO implements Serializable {

    private Long id;

    private String nom;

    private String email;

    private String tel1;

    private String tel2;
    
    private String pays;

    private String codePays1;

    private String codePays2;


    private Long paysId;


    
   
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getCodePays1() {
        return codePays1;
    }

    public void setCodePays1(String codePays1) {
        this.codePays1 = codePays1;
    }

    public String getCodePays2() {
        return codePays2;
    }

    public void setCodePays2(String codePays2) {
        this.codePays2 = codePays2;
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

        TransitaireDTO transitaireDTO = (TransitaireDTO) o;
        if (transitaireDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transitaireDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TransitaireDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", email='" + getEmail() + "'" +
            ", tel1='" + getTel1() + "'" +
            ", tel2='" + getTel2() + "'" +
            ", codePays1='" + getCodePays1() + "'" +
            ", codePays2='" + getCodePays2() + "'" +
            ", pays=" + getPaysId() +
            "}";
    }
}
