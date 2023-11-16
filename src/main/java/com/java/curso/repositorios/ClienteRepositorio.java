package com.java.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.curso.model.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long>{

}
