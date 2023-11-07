package com.java.curso.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="entrada_itens")
public class EntradaItens implements Serializable{
	
	public EntradaItens() {
		super();
	}
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private EntradaProduto entrada;
	@ManyToOne
	private Produto produto;
	private Double quantidade=0.;
	private Double valorProduto=0.;
	private Double valorVenda=0.;
}
