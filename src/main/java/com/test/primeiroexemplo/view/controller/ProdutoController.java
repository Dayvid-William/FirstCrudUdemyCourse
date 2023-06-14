package com.test.primeiroexemplo.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.test.primeiroexemplo.model.Produto;
import com.test.primeiroexemplo.services.ProdutoService;
import com.test.primeiroexemplo.shared.ProdutoDTO;
import com.test.primeiroexemplo.view.model.ProdutoResponse;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
  
  @Autowired
  private ProdutoService produtoService;

  @GetMapping
  public ResponseEntity<List<ProdutoResponse>> obterTodos(){
    //Transforma de list produtomodel para list produtoDTO
    List<ProdutoDTO> produtos = produtoService.obterTodos();
    //Cria o mapper
    ModelMapper mapper = new ModelMapper();
    //Transforma de  list produto DTO em list ProdutoResponse
    List<ProdutoResponse> resposta = produtos.stream()
    .map(produtoDto -> mapper.map(produtoDto, ProdutoResponse.class))
    .collect(Collectors.toList());
    //Retorna a list de produto response com status http ok
    return new ResponseEntity<>(resposta, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<ProdutoResponse>> obterPorId(@PathVariable int id) {
    //try {
      //transforma o Produto model em ProdutoDTO
    Optional<ProdutoDTO> dto = produtoService.obterPorId(id);
    //transforma o produto DTO em ProdutoResponse
    ProdutoResponse produto = new ModelMapper().map(dto.get(), ProdutoResponse.class);
    //Retorna um Optional de produto response com status http Ok
    return new ResponseEntity<>(Optional.of(produto), HttpStatus.OK);
    //} catch (Exception e) {
    //  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //}
    
  }

  @PostMapping
  public Produto adicionar(@RequestBody Produto produto) {
    return produtoService.adicionar(produto);
  }

  @DeleteMapping("/{id}")
  public String deletar(@PathVariable int id) {
    produtoService.deletar(id);
    return "Produto com id:" + id + " foi deletado com sucesso !";
  }

  @PutMapping("/{id}")
  public Produto atualizar(@RequestBody Produto produto, @PathVariable int id){
    return produtoService.atualizar(id, produto);
  }
}
