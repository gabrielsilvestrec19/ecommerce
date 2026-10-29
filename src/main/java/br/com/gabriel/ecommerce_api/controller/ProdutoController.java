package br.com.gabriel.ecommerce_api.controller;
import br.com.gabriel.ecommerce_api.dto.ProdutoRequestDTO;
import br.com.gabriel.ecommerce_api.dto.ProdutoResponseDTO;
import br.com.gabriel.ecommerce_api.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salva(@RequestBody ProdutoRequestDTO dto){
        ProdutoResponseDTO prod = produtoService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(prod);
    }
    @PatchMapping("/{id}/valor")
    public ResponseEntity<ProdutoResponseDTO> atualizaValor(@PathVariable Long id,
                                                            @RequestParam BigDecimal valor){
        ProdutoResponseDTO prod = produtoService.atualizarValor(id, valor);
        return ResponseEntity.ok(prod);
    }
    @PatchMapping("/{id}/quantidade")
    public ResponseEntity<ProdutoResponseDTO> atualizaQtEstoque(@PathVariable Long id,
                                                                @RequestParam Integer qtEstoque){
        ProdutoResponseDTO prod = produtoService.atualizarEstoque(id, qtEstoque);
        return ResponseEntity.ok(prod);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualiza(@PathVariable Long id,
                                                       @RequestBody ProdutoRequestDTO dto){
        ProdutoResponseDTO prod = produtoService.atualizar(id, dto);
        return ResponseEntity.ok(prod);
    }
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listaTodos(){
        List<ProdutoResponseDTO> prod = produtoService.listarTodos();
        return ResponseEntity.ok(prod);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscaPorId(@PathVariable Long id){
        ProdutoResponseDTO prod = produtoService.buscarPorId(id);
        return ResponseEntity.ok(prod);
    }
    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<ProdutoResponseDTO>> buscaPorIdCategoria(@PathVariable Integer id){
        List<ProdutoResponseDTO> prod = produtoService.buscarPorIdCategoria(id);
        return ResponseEntity.ok(prod);
    }
    @GetMapping("/categoria")
    public ResponseEntity<List<ProdutoResponseDTO>> buscaPorNomeCategoria(@RequestParam String nomeCategoria){
        List<ProdutoResponseDTO> prod = produtoService.buscarPorNomeCategoria(nomeCategoria);
        return ResponseEntity.ok(prod);
    }
    @GetMapping("/valor/{menorValor}/{maiorValor}")
    public  ResponseEntity<List<ProdutoResponseDTO>> buscaPorFaixaDePreco(@PathVariable BigDecimal menorValor,
                                                                          @PathVariable BigDecimal maiorValor){
        List<ProdutoResponseDTO> prod = produtoService.buscarPorFaixaDePreco(menorValor,maiorValor);
        return ResponseEntity.ok(prod);
    }
    @GetMapping("/nome")
    public ResponseEntity<List<ProdutoResponseDTO>> buscaPorNome (@RequestParam String nome){
        List<ProdutoResponseDTO> prod = produtoService.buscarPorNome(nome);
        return ResponseEntity.ok(prod);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleta (@PathVariable Long id){
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
