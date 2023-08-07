package bf.gov.courrier.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the NatureMarchand entity.
 */
public class NatureMarchandDTO implements Serializable {

    private Long id;

    private String code;

    private String libelle;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NatureMarchandDTO natureMarchandDTO = (NatureMarchandDTO) o;
        if (natureMarchandDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), natureMarchandDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NatureMarchandDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            "}";
    }
}
