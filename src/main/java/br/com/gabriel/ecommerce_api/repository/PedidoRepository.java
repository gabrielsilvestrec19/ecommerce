package br.com.gabriel.ecommerce_api.repository;

import br.com.gabriel.ecommerce_api.entity.Pedido;
import br.com.gabriel.ecommerce_api.enums.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByClienteId (Long clienteId);
    List<Pedido> findByClienteNome (String clienteNome);
    List<Pedido> findByClienteNomeContainingIgnoreCase(String clienteNome);
    List<Pedido> findAllByStatus (StatusPedido status);
}
