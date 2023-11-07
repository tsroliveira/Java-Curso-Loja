package com.java.curso.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="entrada_produto")
public class EntradaProduto implements Serializable{
	
	public EntradaProduto() {
		super();
	}
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Funcionario funcionario;
	private Date dataEntrada = new Date();
	private String observacao;
	private String fornecedor; //Transformar em Classe Posteriormente;
}
