package br.com.gabriel.ecommerce.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_categoria")
public class Categoria {

    @Id
    @Column(name = "id_categoria", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_categoria", nullable = false, length = 50)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
