package br.com.gabriel.ecommerce_api.entity;

import br.com.gabriel.ecommerce_api.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido",nullable = false, unique = true)
    private Long id;

    @Column(name = "data_pedido",nullable = false)
    private LocalDateTime dataPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido", nullable = false)
    private StatusPedido status;

    @Column(name = "valor_pedido",nullable = false, precision = 10,scale = 2)
    private BigDecimal valorDoPedido;


    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

}
