package br.com.gabriel.ecommerce.service.variante;

import br.com.gabriel.ecommerce.dao.VarianteDAO;
import br.com.gabriel.ecommerce.model.Produto;
import br.com.gabriel.ecommerce.model.Variante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VarianteServiceImpl implements IVarianteService{
    @Autowired
    private VarianteDAO dao;

    @Override
    public Variante adicionarNova(Variante nova) {
        return dao.save(nova);
    }

    @Override
    public Variante alterarDados(Variante variante) {
        return dao.save(variante);
    }

    @Override
    public List<Variante> recuperarPorProduto(Produto p) {
        return dao.findByProduto(p);
    }

    @Override
    public Variante recuperarPeloId(Integer id) {
        return dao.findById(id).orElse(null);
    }
}
