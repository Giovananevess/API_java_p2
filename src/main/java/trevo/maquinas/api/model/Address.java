package trevo.maquinas.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trevo.maquinas.api.dto.AddressDadosDTO;
import java.util.UUID;

@Table(name = "tb_address")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "street", unique = true, nullable = false)
    private String street;

    @Column(name = "cep", unique = true, nullable = false)
    private String cep;

    @OneToOne(mappedBy = "address")
    private User user;

    public Address(AddressDadosDTO dto) {
        this.street = dto.street();
        this.cep = dto.cep();
    }

    public void update(AddressDadosDTO dto) {
        if (dto.street() != null) {
            this.street = dto.street();
        }
        if (dto.cep() != null) {
            this.cep = dto.cep();
        }
    }
}
