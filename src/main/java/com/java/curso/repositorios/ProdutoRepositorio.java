package com.java.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.curso.model.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long>{

}
