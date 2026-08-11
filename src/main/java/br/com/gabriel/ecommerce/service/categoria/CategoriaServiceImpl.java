package br.com.gabriel.ecommerce.service.categoria;

import br.com.gabriel.ecommerce.dao.CategoriaDAO;
import br.com.gabriel.ecommerce.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CategoriaServiceImpl implements ICategoriaService{

    @Autowired
    private CategoriaDAO dao;

    @Override
    public Categoria criarNova(Categoria nova) {
        return dao.save(nova) ;
    }

    @Override
    public Categoria alterar(Categoria categoria) {
        return dao.save(categoria);
    }

    @Override
    public List<Categoria> listarTudo() {
        return dao.findAllByOrderByNameAsc();
    }

    @Override
    public void apagarCategoria(Integer id) {
        dao.deleteById(id);
    }
}
