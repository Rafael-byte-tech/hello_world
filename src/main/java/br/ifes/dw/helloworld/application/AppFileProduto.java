package br.ifes.dw.helloworld.application;

import br.ifes.dw.helloworld.dto.ProdutoInputDTO;
import br.ifes.dw.helloworld.exception.NotFoundException;
import br.ifes.dw.helloworld.exception.VaiMeuFilhoException;
import br.ifes.dw.helloworld.model.Produto;
import br.ifes.dw.helloworld.repository.ProdutoFileRepository;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;

@Component
public class AppFileProduto
{
    private final ArrayList<Produto> produtos;
    private int lastId;
    private final ProdutoFileRepository produtoFileRepository;

    public AppFileProduto()
    {
        this.produtos = new ArrayList<>();
        this.lastId = 0;
        this.produtoFileRepository = new ProdutoFileRepository();
        produtoFileRepository.loadFile(lastId, produtos);
    }

    public Produto create(ProdutoInputDTO produtoInputDTO)
    {
        Produto produto;

        produto = new Produto();

        this.lastId++;

        produto.setId(this.lastId);
        produto.setNome(produtoInputDTO.getNome());
        produto.setPreco(produtoInputDTO.getPreco());

        produtos.add(produto);

        produtoFileRepository.saveFile(lastId, produtos);

        return produto;
    }

    public Produto retrieve(int id) throws NotFoundException, VaiMeuFilhoException
    {
        if (id == 0) throw new VaiMeuFilhoException();

        for (Produto produto : this.produtos)
        {
            if (produto.getId() == id) return produto;
        }

        throw new NotFoundException();
    }

    public ArrayList<Produto> retrieveAll()
    {
        return this.produtos;
    }

    public void update(int id, ProdutoInputDTO produtoInputDTO)
    {
        Produto produto;

        try
        {
            produto = retrieve(id);
            produto.setNome(produtoInputDTO.getNome());
            produto.setPreco(produtoInputDTO.getPreco());

            produtoFileRepository.saveFile(lastId, produtos);
        }
        catch (NotFoundException e)
        {
            e.getMessage();
        }
        catch (VaiMeuFilhoException e)
        {
            e.getMessage();
        }
    }

    public void delete(int id)
    {
        this.produtos.removeIf(produto -> produto.getId() == id);
        produtoFileRepository.saveFile(lastId, produtos);
    }
}