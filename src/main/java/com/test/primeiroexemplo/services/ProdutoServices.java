package com.test.primeiroexemplo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.primeiroexemplo.model.Produto;
import com.test.primeiroexemplo.repository.ProdutoRepository;

@Service
public class ProdutoServices {
  
  @Autowired
  private ProdutoRepository produtorepository;

   /**
   * Metodo para retorna uma lista de produtos
   * @return lista de produtos.
   */
  public List<Produto> obterTodos(){
    return produtorepository.obterTodos();
  }

    /**
   * Metodo que retorna o produto encontrado pelo seu Id.
   * @param id do produto que será localizado.
   * @return Retorna um produto caso seja encontrado
   */
  public Optional<Produto> obterPorId(int id) {
    return produtorepository.obterPorId(id);
  }

   /**
   * Metodo para adicionar produto na lista.
   * @param produto que será adicionado.
   * @return Retorna produto que foi adicionado na lista.
   */
  public Produto adicionar(Produto produto) {
    // Poderia ter alguma regra de negocio aqui para validar o produto.
    return produtorepository.adicionar(produto);
  }

  /**
   * Metodo para deletar o produto por id.
   * @param id do produto que será deletado.
   */
  public void deletar(int id) {
    produtorepository.deletar(id);
  }

  /**
   * Metodo para atualizar um produto na lista.
   * @param produto que será atualizado.
   * @return Retorna o produto atualizado na lista.
   */
  public Produto atualizar(int id, Produto produto) {
    //Ter validação no id
    produto.setId(id);
    
    return produtorepository.atualizar(produto);
  }
}
