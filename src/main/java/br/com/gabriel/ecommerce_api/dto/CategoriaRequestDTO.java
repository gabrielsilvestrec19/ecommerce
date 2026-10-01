package br.com.gabriel.ecommerce_api.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome
) {
}
