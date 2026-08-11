package br.com.gabriel.ecommerce.service.variante;

import br.com.gabriel.ecommerce.model.Produto;
import br.com.gabriel.ecommerce.model.Variante;

import java.util.List;

public interface IVarianteService {
    public Variante adicionarNova(Variante nova);
    public Variante alterarDados(Variante variante);
    public List<Variante> recuperarPorProduto(Produto p);
    public Variante recuperarPeloId(Integer id);
}
