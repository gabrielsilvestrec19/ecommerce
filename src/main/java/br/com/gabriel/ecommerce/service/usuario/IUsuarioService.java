package br.com.gabriel.ecommerce.service.usuario;

import br.com.gabriel.ecommerce.model.Usuario;
import br.com.gabriel.ecommerce.security.ECtoken;

public interface IUsuarioService {
    public Usuario cadastarNovo(Usuario novo);
    public Usuario alterarDados(Usuario usuario);
    public ECtoken fazerLogin (String login, String senha);
}
