package br.com.gabriel.ecommerce_api.dto;
import br.com.gabriel.ecommerce_api.entity.ItemPedido;
import java.math.BigDecimal;

public record ItemPedidoResponseDTO(
    Long id,
    Integer quantidadeDeItensPedidos,
    BigDecimal precoUnitario,
    String produtoNome
) {
    public ItemPedidoResponseDTO(ItemPedido itemPedido){
        this(itemPedido.getId(),
                itemPedido.getQuantidadeDeItensPedidos(),
                itemPedido.getPrecoUnitario(),
                itemPedido.getProduto().getNome());
    }
}
