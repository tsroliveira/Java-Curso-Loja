package com.java.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.curso.model.Compra;

public interface CompraRepositorio extends JpaRepository<Compra, Long>{

}
