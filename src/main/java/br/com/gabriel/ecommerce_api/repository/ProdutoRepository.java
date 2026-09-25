package br.com.gabriel.ecommerce_api.repository;

import br.com.gabriel.ecommerce_api.entity.Categoria;
import br.com.gabriel.ecommerce_api.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findAllByCategoriaNome(String nomeCategoria);
    List<Produto> findAllByCategoriaId(Long idCategoria);
    List<Produto> findByPrecoBetween(BigDecimal valorMenor, BigDecimal valorMaior);
    List<Produto> findByNomeContainingIgnoreCase(String nome);

}
