package br.com.gabriel.ecommerce_api.service.impl;

import br.com.gabriel.ecommerce_api.dto.ProdutoRequestDTO;
import br.com.gabriel.ecommerce_api.dto.ProdutoResponseDTO;
import br.com.gabriel.ecommerce_api.entity.Categoria;
import br.com.gabriel.ecommerce_api.entity.Produto;
import br.com.gabriel.ecommerce_api.mapper.ProdutoMapper;
import br.com.gabriel.ecommerce_api.repository.CategoriaRepository;
import br.com.gabriel.ecommerce_api.repository.ProdutoRepository;
import br.com.gabriel.ecommerce_api.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;
    private final CategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public ProdutoResponseDTO buscarPorId(Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isEmpty()){
            throw new RuntimeException("O produto de id: " + id + " não existe.");
        }
        return produtoMapper.toDTO(produtoOptional.get());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> listarTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoResponseDTO> produtosDto = new ArrayList<>();
        for (Produto prod : produtos){
            produtosDto.add(produtoMapper.toDTO(prod));
        }
        return produtosDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> buscarPorNomeCategoria(String nomeCategoria) {
        List<Produto> produtos = produtoRepository.findAllByCategoriaNomeContainingIgnoreCase(nomeCategoria);
        List<ProdutoResponseDTO> produtosDto = new ArrayList<>();
        for (Produto prod : produtos){
            produtosDto.add(produtoMapper.toDTO(prod));
        }
        return produtosDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> buscarPorIdCategoria(Integer id) {
        List<Produto> produtos = produtoRepository.findAllByCategoriaId(id);
        List<ProdutoResponseDTO> produtosDto = new ArrayList<>();
        for (Produto prod : produtos){
            produtosDto.add(produtoMapper.toDTO(prod));
        }
        return produtosDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> buscarPorFaixaDePreco(BigDecimal valorMenor, BigDecimal valorMaior) {
        List<Produto> produtos = produtoRepository.findByPrecoBetween(valorMenor, valorMaior);
        List<ProdutoResponseDTO> produtosDto = new ArrayList<>();
        for (Produto prod : produtos){
            produtosDto.add(produtoMapper.toDTO(prod));
        }
        return produtosDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> buscarPorNome(String nome) {
        List<Produto> produtos = produtoRepository.findByNomeContainingIgnoreCase(nome);
        List<ProdutoResponseDTO> produtosDto = new ArrayList<>();
        for (Produto prod : produtos){
            produtosDto.add(produtoMapper.toDTO(prod));
        }
        return produtosDto;
    }

    @Override
    @Transactional
    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(dto.categoriaId());
        if (categoriaOptional.isEmpty()) {
            throw new RuntimeException("A categoria com o id: " + dto.categoriaId() + " não existe.");
        }
        Produto produto = produtoMapper.toEntity(dto, categoriaOptional.get());
        Produto produtoSalvo = produtoRepository.save(produto);
        return produtoMapper.toDTO(produtoSalvo);
    }

    @Override
    @Transactional
    public ProdutoResponseDTO atualizarValor(Long id, BigDecimal valor) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isEmpty()){
            throw new RuntimeException("O produto de id: " + id + " não existe.");
        }
        Produto produto = produtoOptional.get();
        produto.setPreco(valor);
        produtoRepository.save(produto);
        return produtoMapper.toDTO(produto);
    }

    @Override
    @Transactional
    public ProdutoResponseDTO atualizarEstoque(Long id, Integer qtEstoque) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isEmpty()){
            throw new RuntimeException("O produto de id: " + id + " não existe.");
        }
        Produto produto = produtoOptional.get();
        produto.setQuantidadeNoEstoque(qtEstoque);
        produtoRepository.save(produto);
        return produtoMapper.toDTO(produto);
    }

    @Override
    @Transactional
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if (optionalProduto.isEmpty()){
            throw new RuntimeException("O produto de id: " + id + " não existe.");
        }
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(dto.categoriaId());
        if (optionalCategoria.isEmpty()){
            throw new RuntimeException("A categoria com o id: " + dto.categoriaId() + " não existe.");
        }
        Produto produto = produtoMapper.toEntity(dto, optionalCategoria.get());
        produto.setId(id);
        Produto produtoSave = produtoRepository.save(produto);
        return produtoMapper.toDTO(produtoSave);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if (optionalProduto.isEmpty()){
            throw new RuntimeException("O produto de id: " + id + " não existe.");
        }
        Produto produto = optionalProduto.get();
        produtoRepository.delete(produto);
    }
}
