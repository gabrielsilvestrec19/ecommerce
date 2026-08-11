package br.com.gabriel.ecommerce.dao;

import br.com.gabriel.ecommerce.model.Produto;
import br.com.gabriel.ecommerce.model.Variante;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VarianteDAO extends CrudRepository <Variante,Integer> {

    public List<Variante> findByProduto(Produto p);
}
