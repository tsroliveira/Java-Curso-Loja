package com.java.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.curso.model.EntradaItens;

public interface EntradaItensRepositorio extends JpaRepository<EntradaItens, Long>{

}
