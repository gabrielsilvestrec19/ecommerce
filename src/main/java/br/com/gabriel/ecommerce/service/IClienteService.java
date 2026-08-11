package br.com.gabriel.ecommerce.service;

import br.com.gabriel.ecommerce.model.Cliente;

import java.util.List;

public interface IClienteService {

    public Cliente cadastrarNovoCLiente(Cliente novo);
    public Cliente alterarCliente(Cliente cliente);
    public Cliente recuperarClientePeloId (Integer id);
    public Cliente recuperarClientePeloTelefone (String telefone);
    public List<Cliente> recuperarTodos ();
}
