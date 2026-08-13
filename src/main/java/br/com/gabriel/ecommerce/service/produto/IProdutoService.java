package br.com.gabriel.ecommerce.service.produto;

import br.com.gabriel.ecommerce.model.Categoria;
import br.com.gabriel.ecommerce.model.Produto;
import org.springframework.data.domain.Page;


import java.util.List;

public interface IProdutoService {
    public Produto cadastrarNovo(Produto novo);
    public Produto alterarProduto(Produto produto);
    public Page<Produto> recuperarTodos(int numPagina);
    public List<Produto> recuperarPorPalavraChave(String palavraChave);
    public Produto buscarPeloId (Integer id);
    public List<Produto> buscarPorCategoria (Categoria categoria);
}
