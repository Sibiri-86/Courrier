package bf.gov.courrier.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TailleBalle entity.
 */
public class TailleBalleDTO implements Serializable {

    private Long id;

    private String code;

    private String libelle;

    private Long volume;


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

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TailleBalleDTO tailleBalleDTO = (TailleBalleDTO) o;
        if (tailleBalleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tailleBalleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TailleBalleDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", volume=" + getVolume() +
            "}";
    }
}
