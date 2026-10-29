package br.com.gabriel.ecommerce_api.controller;

import br.com.gabriel.ecommerce_api.dto.CategoriaRequestDTO;
import br.com.gabriel.ecommerce_api.dto.CategoriaResponseDTO;
import br.com.gabriel.ecommerce_api.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> salva(@RequestBody CategoriaRequestDTO categoriaRequestDTO){
        CategoriaResponseDTO categoria = categoriaService.salvar(categoriaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualiza(@RequestBody CategoriaRequestDTO categoriaRequestDTO,
                                                         @PathVariable Long id){
        CategoriaResponseDTO categoriaResponseDTO = categoriaService.atualizar(id,categoriaRequestDTO);
        return ResponseEntity.ok(categoriaResponseDTO);
    }
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listaTodos(){
        List<CategoriaResponseDTO> categoriaResponseDTO = categoriaService.listarTodos();
        return ResponseEntity.ok(categoriaResponseDTO);
    }
    @GetMapping("/{id}")
    public  ResponseEntity<CategoriaResponseDTO> buscaPorId(@PathVariable Long id){
        CategoriaResponseDTO categoria = categoriaService.buscarPorId(id);
        return ResponseEntity.ok(categoria);
    }
    @GetMapping("/nome/{nome}")
    public  ResponseEntity<CategoriaResponseDTO> buscaPorNome(@PathVariable String nome){
        CategoriaResponseDTO categoria = categoriaService.buscarPeloNome(nome);
        return ResponseEntity.ok(categoria);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaPorId (@PathVariable Long id){
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/nome/{nome}")
    public ResponseEntity<Void> deletaPorNome (@PathVariable String nome){
        categoriaService.deletarPeloNome(nome);
        return ResponseEntity.noContent().build();
    }
}
