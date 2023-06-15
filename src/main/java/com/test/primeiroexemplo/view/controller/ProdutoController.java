package com.test.primeiroexemplo.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.test.primeiroexemplo.view.model.ProdutoRequest;
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
    Optional<ProdutoDTO> produtoDto = produtoService.obterPorId(id);
    //transforma o produto DTO em ProdutoResponse
    ProdutoResponse produto = new ModelMapper().map(produtoDto.get(), ProdutoResponse.class);
    //Retorna um Optional de produto response com status http Ok
    return new ResponseEntity<>(Optional.of(produto), HttpStatus.OK);
    //} catch (Exception e) {
    //  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //}
    
  }

  @PostMapping
  public ResponseEntity<ProdutoResponse> adicionar(@RequestBody ProdutoRequest produtoReq) {
    ModelMapper mapper = new ModelMapper();
    //converte o produto request em produto dto
    ProdutoDTO produtoDto = mapper.map(produtoReq, ProdutoDTO.class);
    //Adiciona o produto dto no banco de dados
    produtoDto = produtoService.adicionar(produtoDto);
    //retorna com response entity para poder retorna o status de CREATED e converte o produto adicionado em produto response para retorna ao usuario
    return new ResponseEntity<>(mapper.map(produtoDto, ProdutoResponse.class), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  //Retorna um response entity de qualquer coisa (coringa)
  public ResponseEntity<?> deletar(@PathVariable Integer id) {
    //Deleta o produto por id
    produtoService.deletar(id);
    //Retorna um response entity com status de no content (informando assim que o produto de id especifico foi deletado)
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProdutoResponse> atualizar(@RequestBody ProdutoRequest produtoReq, @PathVariable int id){
    //Cria o model mapper ("conversor")
    ModelMapper mapper = new ModelMapper();
    //Converte o produto request em produto Dto 
    ProdutoDTO produtoDto = mapper.map(produtoReq, ProdutoDTO.class);
    //Atribui ao produto DTO o produto que está vindo de resposta do metodo atualizar vindo de ProdutoService 
    produtoDto = produtoService.atualizar(id, produtoDto);
    //Converte o produto dto em produto response e envia o status Http ok
    return new ResponseEntity<>(
    mapper.map(produtoDto, ProdutoResponse.class),
    HttpStatus.OK);
  }
}
