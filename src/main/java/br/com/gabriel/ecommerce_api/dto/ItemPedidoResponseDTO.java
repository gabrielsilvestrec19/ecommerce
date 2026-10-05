package br.com.gabriel.ecommerce_api.dto;
import java.math.BigDecimal;

public record ItemPedidoResponseDTO(
    Long id,
    Integer quantidadeDeItensPedidos,
    BigDecimal precoUnitario,
    String produtoNome
) {

}
