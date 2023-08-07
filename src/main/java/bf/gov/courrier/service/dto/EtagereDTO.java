package bf.gov.courrier.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Etagere entity.
 */
public class EtagereDTO implements Serializable {

    private Long id;

    private String numero;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
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

        EtagereDTO etagereDTO = (EtagereDTO) o;
        if (etagereDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etagereDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EtagereDTO{" +
            "id=" + getId() +
            ", numero=" + getNumero() +
            "}";
    }
}
