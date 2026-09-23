package br.com.gabriel.ecommerce_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria", unique = true, nullable = false)
    private Long id;

    @Column(name = "nome_categoria", nullable = false,unique = true, length = 45)
    private String nome;

}
