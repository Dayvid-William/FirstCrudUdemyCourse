package com.test.primeiroexemplo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.test.primeiroexemplo.model.Produto;

@Repository
public class ProdutoRepository {
  
  private List<Produto> produtos = new ArrayList<Produto>();
  private int ultimoId = 0;

  /**
   * Metodo para retorna uma lista de produtos
   * @return lista de produtos.
   */
  public List<Produto> obterTodos(){
    return produtos;
  }

  /**
   * Metodo que retorna o produto encontrado pelo seu Id.
   * @param id do produto que será localizado.
   * @return Retorna um produto caso seja encontrado
   */
  public Optional<Produto> obterPorId(int id) {
    return produtos
        .stream()
        .filter(produto -> produto.getId() == id)
        .findFirst();
  }

  /**
   * Metodo para adicionar produto na lista.
   * @param produto que será adicionado.
   * @return Retorna produto que foi adicionado na lista.
   */
  public Produto adicionar(Produto produto) {
    
    ultimoId ++;

    produto.setId(ultimoId);
    produtos.add(produto);

    return produto;
  }
  
}
