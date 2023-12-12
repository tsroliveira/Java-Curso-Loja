package com.java.curso.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="produtos")
public class Produto implements Serializable{
	
	public Produto() {
		super();
	}
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String titulo;
	private String descricao;
	private Double valorVenda;
	private String categoria;	//Criar um objeto somente para categoria
	private String marca;		//Criar um objeto somente para marca
	private Double quantidadeEstoque=0.;
	private String nomeImagem; //Em um cenário real, criariamos uma entidade que conteria diversas imagens, não somente uma;
}
