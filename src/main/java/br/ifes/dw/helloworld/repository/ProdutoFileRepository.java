package br.ifes.dw.helloworld.repository;

import br.ifes.dw.helloworld.model.Produto;

import java.io.*;
import java.util.ArrayList;

public class ProdutoFileRepository
{
    public void loadFile(int lastId, ArrayList<Produto> produtos)
    {
        try
        {
            FileInputStream inputStream;
            DataInputStream input;
            int numberOfProdutos;

            inputStream = new FileInputStream("data.bin");
            input = new DataInputStream(inputStream);

            lastId = input.readInt();
            numberOfProdutos = input.readInt();

            for (int i=0; i<numberOfProdutos; i++)
            {
                Produto produto = new Produto();

                produto.setId(input.readInt());
                produto.setNome(input.readUTF());
                produto.setPreco(input.readDouble());

                produtos.add(produto);
            }

            inputStream.close();
            input.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void saveFile(int lastId, ArrayList<Produto> produtos)
    {
        try
        {
            FileOutputStream outputStream;
            DataOutputStream output;

            outputStream = new FileOutputStream("data.bin");
            output = new DataOutputStream(outputStream);

            output.writeInt(lastId);
            output.writeInt(produtos.size());

            for(Produto produto : produtos)
            {
                output.writeInt(produto.getId());
                output.writeUTF(produto.getNome());
                output.writeDouble(produto.getPreco());
            }

            outputStream.close();
            output.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
