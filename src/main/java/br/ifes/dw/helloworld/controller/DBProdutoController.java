package br.ifes.dw.helloworld.controller;

import br.ifes.dw.helloworld.application.AppDBProduto;
import br.ifes.dw.helloworld.dto.ProdutoInputDTO;
import br.ifes.dw.helloworld.exception.NotFoundException;
import br.ifes.dw.helloworld.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("db_produtos")
public class DBProdutoController
{
    @Autowired
    private AppDBProduto appDBProduto;

    @PostMapping("create")
    public Produto create(@RequestBody ProdutoInputDTO produtoInputDTO)
    {
        return appDBProduto.create(produtoInputDTO);
    }

    @GetMapping("retrieve/{id}")
    public Produto retrieve(@PathVariable int id)
    {
        try
        {
            return appDBProduto.retrieve(id);
        }
        catch (NotFoundException e)
        {
            e.getMessage();
            return null;
        }
    }

    @GetMapping("retrieve_all")
    public List<Produto> retrieveAll()
    {
        return appDBProduto.retrieveAll();
    }

    @PutMapping("update/{id}")
    public void update(@PathVariable int id, @RequestBody ProdutoInputDTO produtoInputDTO)
    {
        appDBProduto.update(id, produtoInputDTO);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable int id)
    {
        appDBProduto.delete(id);
    }
}
