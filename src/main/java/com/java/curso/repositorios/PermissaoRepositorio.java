package com.java.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.curso.model.Permissao;

public interface PermissaoRepositorio extends JpaRepository<Permissao, Long>{

}
