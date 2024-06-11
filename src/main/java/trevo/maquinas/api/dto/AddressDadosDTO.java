package trevo.maquinas.api.dto;

import jakarta.validation.constraints.NotEmpty;

public record AddressDadosDTO(
            @NotEmpty(message = "O campo de Endereço não pode estar vazio")
            String street,

            @NotEmpty(message = "O campo de CEP não pode estar vazio")
            String cep
){}
