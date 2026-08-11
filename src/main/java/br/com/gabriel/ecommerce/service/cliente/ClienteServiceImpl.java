package br.com.gabriel.ecommerce.service.cliente;

import br.com.gabriel.ecommerce.dao.ClienteDAO;
import br.com.gabriel.ecommerce.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ClienteServiceImpl implements IClienteService{

    @Autowired
    private ClienteDAO cliDao;

    @Override
    public Cliente cadastrarNovoCLiente(Cliente novo) {
        return cliDao.save(novo);
    }

    @Override
    public Cliente alterarCliente(Cliente cliente) {
        return cliDao.save(cliente);
    }

    @Override
    public Cliente recuperarClientePeloId(Integer id) {
        return cliDao.findById(id).orElse(null);
    }

    @Override
    public Cliente recuperarClientePeloTelefone(String telefone) {
        return cliDao.findByTelefone(telefone);
    }

    @Override
    public List<Cliente> recuperarTodos() {
        return (List<Cliente>) cliDao.findAll();
    }
}
