package com.test.primeiroexemplo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.primeiroexemplo.model.Produto;
import com.test.primeiroexemplo.model.exception.ResourceNotFoundException;
import com.test.primeiroexemplo.repository.ProdutoRepository;
import com.test.primeiroexemplo.shared.ProdutoDTO;

@Service
public class ProdutoService {
  
  @Autowired
  private ProdutoRepository produtoRepository;

  /**
   * Metodo para retorna uma lista de produtos
   * @return lista de produtos.
   */
  public List<ProdutoDTO> obterTodos(){
    //Colocar Regra aqui caso tenha...

    //Retorna uma lista de produtos model.
    List<Produto> produtos = produtoRepository.findAll();

    return produtos.stream()
    .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
    .collect(Collectors.toList());
  }

  
  /**
   * Metodo que retorna o produto encontrado pelo seu Id.
   * @param id do produto que será localizado.
   * @return Retorna um produto caso seja encontrado
   */
  public Optional<ProdutoDTO> obterPorId(int id) {
    //Obtendo optional de produto por id.
    Optional<Produto> produto = produtoRepository.findById(id);
    //Se não encontra, lança exception.
    if(produto.isEmpty()){
      throw new ResourceNotFoundException("Produto com id: " + id + " não encontrado");
    }
    //Convertendo o meu optional de produto em um produto dto.
    ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);
    //Criando e retornando um optional de produto dto.
    return Optional.of(dto);
  }

  /**
   * Metodo para adicionar produto na lista.
   * @param produto que será adicionado.
   * @return Retorna produto que foi adicionado na lista.
   */
  public ProdutoDTO adicionar(ProdutoDTO produtoDTO) {
    // Removendo o id para conseguir fazer cadastro.


    return produtoRepository.save(produto);
  }

  /**
   * Metodo para deletar o produto por id.
   * @param id do produto que será deletado.
   */
  public void deletar(int id) {
    //Poderia ter alguma logica de validação
    produtoRepository.deleteById(id);
  }

  /**
   * Metodo para atualizar um produto na lista.
   * @param produto que será atualizado.
   * @param id do produto
   * @return Retorna o produto atualizado na lista.
   */
  public ProdutoDTO atualizar(int id, ProdutoDTO produto) {
    
    produto.setId(id);

    return produtoRepository.save(produto);
  }

}
