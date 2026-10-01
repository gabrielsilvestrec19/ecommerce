package br.com.gabriel.ecommerce_api.dto;

public record ClienteResponseDTO(
        Long id,
        String email,
        String nome
) {
}
