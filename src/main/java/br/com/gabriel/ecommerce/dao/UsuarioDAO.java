package br.com.gabriel.ecommerce.dao;

import br.com.gabriel.ecommerce.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDAO extends CrudRepository <Usuario, Integer> {

    public Usuario findByLogin(String login);

}
