package br.com.gabriel.ecommerce.service.pedido;

import br.com.gabriel.ecommerce.model.Pedido;

import java.util.List;

public interface IPedidoService {

    public Pedido criarNovoPedido(Pedido pedido);
    public Pedido alterarDados(Pedido pedido);
    public List<Pedido> recuperarTodos();
    public Pedido recuperarPeloNumero(Integer numPedido);
    public List<Pedido> recuperarPeloStatus(Integer status);
}
