package br.com.gabriel.ecommerce.service.pedido;

import br.com.gabriel.ecommerce.controller.ItemPedido;
import br.com.gabriel.ecommerce.dao.PedidoDAO;
import br.com.gabriel.ecommerce.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PedidoServiceImpl implements IPedidoService{

    @Autowired
    private PedidoDAO dao;

    @Override
    public Pedido criarNovoPedido(Pedido pedido) {
        pedido.setStatus(1);
        for (ItemPedido item : pedido.getItens()){
            item.setPedido(pedido);
        }
        double total = 0.0, desconto = 0.0;
        for (ItemPedido item : pedido.getItens()){
            total += item.getValorTotal();
        }
        pedido.setValorBruto(total);
        if (total>=30.0){
            desconto = total*0*10;
        }
        total = total - desconto;

        pedido.setDesconto(desconto);
        pedido.setValorTotal(total);
        return dao.save(pedido);
    }

    @Override
    public Pedido alterarDados(Pedido pedido) {
        return dao.save(pedido);
    }

    @Override
    public List<Pedido> recuperarTodos() {
        return (List<Pedido>)dao.findAll();
    }

    @Override
    public Pedido recuperarPeloNumero(Integer numPedido) {
        return dao.findById(numPedido).orElse(null);
    }

    @Override
    public List<Pedido> recuperarPeloStatus(Integer status) {
        return dao.findAllByStatus(status);
    }
}
