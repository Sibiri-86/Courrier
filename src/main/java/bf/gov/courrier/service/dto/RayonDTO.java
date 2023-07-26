package bf.gov.courrier.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Rayon entity.
 */
public class RayonDTO implements Serializable {

    private Long id;

    private Long numero;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RayonDTO rayonDTO = (RayonDTO) o;
        if (rayonDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rayonDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RayonDTO{" +
            "id=" + getId() +
            ", numero=" + getNumero() +
            "}";
    }
}
