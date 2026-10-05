package br.com.gabriel.ecommerce_api.mapper;

import br.com.gabriel.ecommerce_api.dto.ItemPedidoRequestDTO;
import br.com.gabriel.ecommerce_api.dto.ItemPedidoResponseDTO;
import br.com.gabriel.ecommerce_api.entity.ItemPedido;
import br.com.gabriel.ecommerce_api.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoMapper {

    public ItemPedidoResponseDTO toDTO(ItemPedido itemPedido){

        if (itemPedido == null){
            return null;
        }
        return new ItemPedidoResponseDTO(itemPedido.getId(),
                itemPedido.getQuantidadeDeItensPedidos(),
                itemPedido.getPrecoUnitario(),
                itemPedido.getProduto()!= null ? itemPedido.getProduto().getNome() : null);
    }

    public ItemPedido toEntity(ItemPedidoRequestDTO dto, Produto produto) {
        if (dto == null) {
            return null;
        }

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setQuantidadeDeItensPedidos(dto.quantidadeDeItensPedidos());

        if (produto != null) {
            itemPedido.setProduto(produto);
            itemPedido.setPrecoUnitario(produto.getPreco());
        }

        return itemPedido;
    }
}
