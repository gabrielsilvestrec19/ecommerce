package br.com.gabriel.ecommerce.controller;

import br.com.gabriel.ecommerce.EcommerceApplication;
import br.com.gabriel.ecommerce.model.Usuario;
import br.com.gabriel.ecommerce.security.ECtoken;
import br.com.gabriel.ecommerce.service.usuario.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {
    @Autowired
    private IUsuarioService service;

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> cadastrarNovo (@RequestBody Usuario novo){
        Usuario res = service.cadastarNovo(novo);
        if (res != null){
            return ResponseEntity.status(201).body(res);
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> alterarDados(@RequestBody Usuario usuario, @PathVariable Integer id){
        usuario.setId(id);
        Usuario res = service.alterarDados(usuario);
        if(res != null){
            return ResponseEntity.status(201).body(res);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<ECtoken> realizarLogin (@RequestBody Usuario usuario){
        ECtoken token = service.fazerLogin(usuario.getLogin(), usuario.getSenha());
        if (token != null){
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(403).build();
    }

}
