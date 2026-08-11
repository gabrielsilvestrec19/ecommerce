package br.com.gabriel.ecommerce.service.produto;

import br.com.gabriel.ecommerce.model.Categoria;
import br.com.gabriel.ecommerce.model.Produto;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface IProdutoService {
    public Produto cadastrarNovo(Produto novo);
    public Produto alterarProduto(Produto produto);
    public List<Produto> recuperarTodos();
    public List<Produto> recuperarPorPalavraChave(String palavraChave);
    public Produto buscarPeloId (Integer id);
    public List<Produto> buscarPorCategoria (Categoria categoria);
}
