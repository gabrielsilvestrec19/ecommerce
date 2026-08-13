package br.com.gabriel.ecommerce.controller;

import br.com.gabriel.ecommerce.model.Pedido;
import br.com.gabriel.ecommerce.service.pedido.IPedidoService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PedidoController {

    @Autowired
    private IPedidoService service;

    @PostMapping("/pedidos")
    public ResponseEntity<Pedido> inserirNovo (@RequestBody Pedido pedido){
        Pedido res = service.criarNovoPedido(pedido);
        if (res != null){
           return ResponseEntity.status(201).body(res);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedido>> recuperarTodos(){
        return ResponseEntity.ok(service.recuperarTodos());
    }
    @GetMapping("/pedidos/{id}")
    public ResponseEntity<Pedido> recuperarPeloId(@PathVariable Integer id){
        Pedido res = service.recuperarPeloNumero(id);
        if (res != null){
            ResponseEntity.ok(res);
        }
        return ResponseEntity.notFound().build();
    }

}