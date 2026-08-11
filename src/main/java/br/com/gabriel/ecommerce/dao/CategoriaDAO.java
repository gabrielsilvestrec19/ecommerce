package br.com.gabriel.ecommerce.dao;

import br.com.gabriel.ecommerce.model.Categoria;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoriaDAO extends CrudRepository <Categoria, Integer> {

    public List<Categoria> findAllByOrderByNameAsc();

}
