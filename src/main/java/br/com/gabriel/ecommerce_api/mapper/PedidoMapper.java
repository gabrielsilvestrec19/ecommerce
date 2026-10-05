package br.com.gabriel.ecommerce_api.mapper;

import br.com.gabriel.ecommerce_api.dto.ItemPedidoResponseDTO;
import br.com.gabriel.ecommerce_api.dto.PedidoResponseDTO;
import br.com.gabriel.ecommerce_api.entity.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PedidoMapper {

    private final ItemPedidoMapper itemPedidoMapper;

    public PedidoResponseDTO toDTO(Pedido pedido) {
        if (pedido == null) {
            return null;
        }

        List<ItemPedidoResponseDTO> itensDTO = pedido.getItens() != null
                ? pedido.getItens().stream()
                .map(itemPedidoMapper::toDTO)
                .toList()
                : Collections.emptyList();

        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getDataPedido(),
                pedido.getStatus(),
                pedido.getValorDoPedido(),
                pedido.getCliente() != null ? pedido.getCliente().getId() : null,
                itensDTO
        );
    }
}