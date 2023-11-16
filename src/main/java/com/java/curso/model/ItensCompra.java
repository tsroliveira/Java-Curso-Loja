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
@Table(name="itens_compra")
public class ItensCompra implements Serializable{
	
	public ItensCompra() {
		super();
	}
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Produto produto;

	@ManyToOne
	private Compra compra;

	private Integer quantidade=0;

	private Double valorUnitario;
	
	private Double valorTotal;

}
