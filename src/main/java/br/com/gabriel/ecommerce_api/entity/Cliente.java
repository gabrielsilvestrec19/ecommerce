package br.com.gabriel.ecommerce_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false, unique = true)
    private Long id;

    @Column(name = "nome_cliente", nullable = false, length = 255)
    private String nome;

    @Column(name = "email_cliente", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "cpf_cliente", nullable = false, unique = true, length = 15)
    private String cpf;

}
