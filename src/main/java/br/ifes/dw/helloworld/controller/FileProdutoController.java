package br.ifes.dw.helloworld.controller;

import br.ifes.dw.helloworld.application.AppFileProduto;
import br.ifes.dw.helloworld.dto.ProdutoInputDTO;
import br.ifes.dw.helloworld.exception.NotFoundException;
import br.ifes.dw.helloworld.exception.VaiMeuFilhoException;
import br.ifes.dw.helloworld.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("file_produtos")
public class FileProdutoController
{
    @Autowired
    private final AppFileProduto appFileProduto = new AppFileProduto();

    @PostMapping("file_produtos/create")
    public Produto create(@RequestBody ProdutoInputDTO produtoInputDTO)
    {
        return appFileProduto.create(produtoInputDTO);
    }

    @GetMapping("file_produtos/{id}")
    public Produto retrieve(@PathVariable int id)
    {
        try
        {
            return appFileProduto.retrieve(id);
        }
        catch (NotFoundException e)
        {
            e.getMessage();
            return null;
        }
        catch (VaiMeuFilhoException e)
        {
            e.getMessage();
            return null;
        }
    }

    @GetMapping("file_produtos/retrieve_all")
    public ArrayList<Produto> retrieveAll()
    {
        return appFileProduto.retrieveAll();
    }

    @PutMapping("file_produtos/update/{id}")
    public void update(@PathVariable int id, @RequestBody ProdutoInputDTO produtoInputDTO)
    {
        appFileProduto.update(id, produtoInputDTO);
    }

    @DeleteMapping("file_produtos/delete/{id}")
    public void delete(@PathVariable int id)
    {
        appFileProduto.delete(id);
    }
}
