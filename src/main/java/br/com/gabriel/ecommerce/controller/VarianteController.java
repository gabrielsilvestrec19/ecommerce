package br.com.gabriel.ecommerce.controller;

import br.com.gabriel.ecommerce.model.Produto;
import br.com.gabriel.ecommerce.model.Variante;
import br.com.gabriel.ecommerce.service.variante.IVarianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VarianteController {
    @Autowired
    private IVarianteService service;

    @PostMapping("/variantes")
    public ResponseEntity<Variante> adicionar(@RequestBody Variante variante){
        Variante res = service.adicionarNova(variante);
        if (res != null){
            return ResponseEntity.status(201).body(res);
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/variantes/{id}")
    public ResponseEntity<Variante> modificar(@RequestBody Variante variante,@PathVariable Integer id){
        variante.setId(id);
        Variante res = service.alterarDados(variante);
        if (res != null){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/variantes/{id}")
    public ResponseEntity<Variante> recuperarPeloId(@PathVariable Integer id){
        Variante var = service.recuperarPeloId(id);
        if(var != null){
            return ResponseEntity.ok(var);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/variantes")
    public ResponseEntity<List<Variante>> recuperaPeloProduto(@RequestParam(name = "idproduto") Integer idProduto){
        Produto p = new Produto();
        p.setId(idProduto);
        return ResponseEntity.ok(service.recuperarPorProduto(p));
    }


}
