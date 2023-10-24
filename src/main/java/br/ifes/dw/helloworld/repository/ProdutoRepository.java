package br.ifes.dw.helloworld.repository;

import br.ifes.dw.helloworld.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>
{
}