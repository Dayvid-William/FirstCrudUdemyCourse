package com.test.primeiroexemplo.model;

public class Produto {
  
  //#region Atributos
  private int id;

  private String nome;

  private int quantidade;

  private Double valor;

  private String observacao;
  //#endregion

  //#region GettersAndSetters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

  public String getObservacao() {
    return observacao;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }
  //#endregion
  
}
