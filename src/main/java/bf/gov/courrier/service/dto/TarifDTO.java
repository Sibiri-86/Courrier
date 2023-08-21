package bf.gov.courrier.service.dto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Tarif entity.
 */
public class TarifDTO implements Serializable {

    private Long id;

    private BigDecimal montant;
    private Long volume;


    private Long tailleBalleId;
    
  

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Long getTailleBalleId() {
        return tailleBalleId;
    }

    public void setTailleBalleId(Long tailleBalleId) {
        this.tailleBalleId = tailleBalleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TarifDTO tarifDTO = (TarifDTO) o;
        if (tarifDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tarifDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TarifDTO{" +
            "id=" + getId() +
            ", montant=" + getMontant() +
            ", tailleBalle=" + getTailleBalleId() +
            "}";
    }
}
