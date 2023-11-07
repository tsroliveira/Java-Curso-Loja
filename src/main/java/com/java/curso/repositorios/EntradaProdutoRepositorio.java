package com.java.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.curso.model.EntradaProduto;

public interface EntradaProdutoRepositorio extends JpaRepository<EntradaProduto, Long>{

}
