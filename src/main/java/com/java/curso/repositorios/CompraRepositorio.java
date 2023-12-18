package com.java.curso.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.curso.model.Compra;

public interface CompraRepositorio extends JpaRepository<Compra, Long>{

	//@Query("from Compra where client_id=?1")
	//public List<Compra> buscarComprasCliente(Long id);
}
