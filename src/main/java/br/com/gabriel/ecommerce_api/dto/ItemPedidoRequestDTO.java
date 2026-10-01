package br.com.gabriel.ecommerce_api.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
public record ItemPedidoRequestDTO(

        @Positive(message = "A quantidade deve ser maior que zero")
        @NotNull(message = "É necessário informar a quantidade de itens pedidos")
        Integer quantidadeDeItensPedidos,


        @NotNull(message = "É necessário informar o id do produto")
        Long produtoId

) {
}
