package com.test.primeiroexemplo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.primeiroexemplo.model.Produto;
import com.test.primeiroexemplo.repository.ProdutoRepository_old;

@Service
public class ProdutoService {
  
  @Autowired
  private ProdutoRepository_old produtoRepository;

  /**
   * Metodo para retorna uma lista de produtos
   * @return lista de produtos.
   */
  public List<Produto> obterTodos(){
    //Colocar Regra aqui caso tenha...
    return produtoRepository.obterTodos();
  }

  
  /**
   * Metodo que retorna o produto encontrado pelo seu Id.
   * @param id do produto que será localizado.
   * @return Retorna um produto caso seja encontrado
   */
  public Optional<Produto> obterPorId(int id) {
    return produtoRepository.obterPorId(id);
  }

  /**
   * Metodo para adicionar produto na lista.
   * @param produto que será adicionado.
   * @return Retorna produto que foi adicionado na lista.
   */
  public Produto adicionar(Produto produto) {
    // Poderia ter alguma regra de negocio para validar o produto.
    return produtoRepository.adicionar(produto);
  }

  /**
   * Metodo para deletar o produto por id.
   * @param id do produto que será deletado.
   */
  public void deletar(int id) {
    //Poderia ter alguma logica de validação
    produtoRepository.deletar(id);
  }

  /**
   * Metodo para atualizar um produto na lista.
   * @param produto que será atualizado.
   * @param id do produto
   * @return Retorna o produto atualizado na lista.
   */
  public Produto atualizar(int id, Produto produto) {
    
    produto.setId(id);

    return produtoRepository.atualizar(produto);
  }

}
