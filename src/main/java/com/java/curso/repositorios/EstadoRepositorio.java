package com.java.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.curso.model.Estado;

public interface EstadoRepositorio extends JpaRepository<Estado, Long>{

}
