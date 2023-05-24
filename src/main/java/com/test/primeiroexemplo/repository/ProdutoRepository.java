package com.test.primeiroexemplo.repository;

import org.springframework.stereotype.Repository;

import com.test.primeiroexemplo.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
  
}
