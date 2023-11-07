package com.java.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.curso.model.Funcionario;

public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long>{
	Funcionario findByEmail(String email);
}
