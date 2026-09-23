package br.com.gabriel.ecommerce_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto", nullable = false, unique = true)
    private Long id;

    @Column(name = "nome_produto",length = 150, nullable = false, unique = true)
    private String nome;

    @Column(name = "preco_produto", nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "qt_estoque", nullable = false)
    private Integer quantidadeNoEstoque;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

}
