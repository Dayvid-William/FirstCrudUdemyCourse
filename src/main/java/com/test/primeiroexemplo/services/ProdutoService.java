package com.test.primeiroexemplo.services;

import java.util.List;
import java.util.Optional;
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
  public ProdutoDTO adicionar(ProdutoDTO produtoDto) {
    // Removendo o id para conseguir fazer cadastro.
    produtoDto.setId((Integer) null);
    //Criar um objeto de mapeamento
    ModelMapper mapper = new ModelMapper();
    //Converter o produto dto em um produto
    Produto produto = mapper.map(produtoDto, Produto.class);
    //Salvar o produto no banco 
    produto  = produtoRepository.save(produto);

    produtoDto.setId(produto.getId());
    //Retorna o produto DTO atualizado
    return produtoDto;
  }

  /**
   * Metodo para deletar o produto por id.
   * @param id do produto que será deletado.
   */
  public void deletar(int id) {
    //Verificar se o produto existe
    Optional<Produto> produto  = produtoRepository.findById(id);
    //Se não existir lança uma exception
    if(produto.isEmpty()){
      throw new ResourceNotFoundException("Não foi possivel encontrar o produto com o id: " + id + " - Produto não existe");
    }
    produtoRepository.deleteById(id);
  }

  /**
   * Metodo para atualizar um produto na lista.
   * @param produto que será atualizado.
   * @param id do produto
   * @return Retorna o produto atualizado na lista.
   */
  public ProdutoDTO atualizar(int id, ProdutoDTO produtoDto) {
    //Passar o id para o produto dto
    produtoDto.setId(id);
    //Criar um objeto de mapeamento
    ModelMapper mapper = new ModelMapper();
    //Converter o dto em um produto
    Produto produto = mapper.map(produtoDto, Produto.class);
    //Atualizar o produto no banco de dados
    produtoRepository.save(produto);
    //Retorna o produto dto atualizado
    return produtoDto;
  }

}
