package br.com.gabriel.ecommerce_api.dto;
import br.com.gabriel.ecommerce_api.enums.StatusPedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDTO(
        Long id,
        LocalDateTime dataPedido,
        StatusPedido status,
        BigDecimal valorDoPedido,
        Long clienteId,
        List<ItemPedidoResponseDTO> itens


) {
}
