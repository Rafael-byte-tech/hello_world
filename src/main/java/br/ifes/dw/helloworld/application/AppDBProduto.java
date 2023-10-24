package br.ifes.dw.helloworld.application;

import br.ifes.dw.helloworld.dto.ProdutoInputDTO;
import br.ifes.dw.helloworld.exception.NotFoundException;
import br.ifes.dw.helloworld.model.Produto;
import br.ifes.dw.helloworld.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class AppDBProduto
{
    @Autowired
    ProdutoRepository produtoRepository;
    public Produto create(ProdutoInputDTO produtoInputDTO)
    {
        Produto produto = new Produto();

        produto.setNome(produtoInputDTO.getNome());
        produto.setPreco(produtoInputDTO.getPreco());

        return produtoRepository.save(produto);
    }

    public Produto retrieve(int id) throws NotFoundException
    {
        Produto produto;
        Optional<Produto> produtoEntity;

        produtoEntity = produtoRepository.findById(id);

        if (produtoEntity.isPresent())
        {
            produto = produtoEntity.get();
        }
        else
        {
            throw new NotFoundException();
        }

        return produto;
    }

    public List<Produto> retrieveAll()
    {
        return produtoRepository.findAll();
    }

    public void update(int id, ProdutoInputDTO produtoInputDTO)
    {
        Produto produto;

        try
        {
            produto = retrieve(id);
            produto.setNome(produtoInputDTO.getNome());
            produto.setPreco(produtoInputDTO.getPreco());

            produtoRepository.save(produto);
        }
        catch (NotFoundException e)
        {
            e.getMessage();
        }
    }

    public void delete(int id)
    {
        produtoRepository.deleteById(id);
    }
}