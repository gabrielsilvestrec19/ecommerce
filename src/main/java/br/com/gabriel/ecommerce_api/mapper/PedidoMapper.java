package br.com.gabriel.ecommerce_api.mapper;

import br.com.gabriel.ecommerce_api.dto.ItemPedidoResponseDTO;
import br.com.gabriel.ecommerce_api.dto.PedidoResponseDTO;
import br.com.gabriel.ecommerce_api.entity.ItemPedido;
import br.com.gabriel.ecommerce_api.entity.Pedido;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class PedidoMapper {

    public PedidoResponseDTO toDTO(Pedido pedido){

        if (pedido == null){
            return null;
        }

        List<ItemPedidoResponseDTO> itensDTO = new ArrayList<>();
        if (pedido.getItens() != null){
            for(ItemPedido item : pedido.getItens()){

                ItemPedidoResponseDTO itemConvertido = toDTO(item);

                itensDTO.add(itemConvertido);
            }
        }


        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getDataPedido(),
                pedido.getStatus(),
                pedido.getValorDoPedido(),
                pedido.getCliente() != null ? pedido.getCliente().getId() : null,
                itensDTO
        );
    }
    public ItemPedidoResponseDTO toDTO(ItemPedido itemPedido){
        if(itemPedido == null){
            return null;
        }
        return new ItemPedidoResponseDTO(
                itemPedido.getId(),
                itemPedido.getQuantidadeDeItensPedidos(),
                itemPedido.getPrecoUnitario(),
                itemPedido.getProduto() != null ? itemPedido.getProduto().getNome() : null
        );
    }
}
