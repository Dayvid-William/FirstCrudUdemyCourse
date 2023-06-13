package com.test.primeiroexemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.primeiroexemplo.model.Produto;

// Primeiro parametro e o objeto que no caso e do tipo Produto segundo e o tipo da primary key que no caso e um int por ser id
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
