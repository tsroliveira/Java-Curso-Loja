package com.java.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.curso.model.Cidade;

public interface CidadeRepositorio extends JpaRepository<Cidade, Long>{

}
