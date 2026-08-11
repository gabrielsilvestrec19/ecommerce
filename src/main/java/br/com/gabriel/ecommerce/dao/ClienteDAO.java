package br.com.gabriel.ecommerce.dao;

import br.com.gabriel.ecommerce.model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDAO extends CrudRepository <Cliente, Integer>{

    public Cliente findByTelefone(String telefone);
}
