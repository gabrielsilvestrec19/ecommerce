package br.com.gabriel.ecommerce_api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PedidoRequestDTO(

        @NotNull(message = "O ID do cliente é obriagtório")
        Long clienteId,

        @NotNull(message = "A lista de itens não pode ser nula")
        @NotEmpty(message = "O pedido deve conter pelo menos um item")
        @Valid
        List<ItemPedidoRequestDTO> itens
) {
}
