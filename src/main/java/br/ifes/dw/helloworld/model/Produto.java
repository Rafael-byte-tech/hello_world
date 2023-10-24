package br.ifes.dw.helloworld.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Produto
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String nome;
  private double preco;

}
