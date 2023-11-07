package com.java.curso.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
@Table(name="funcionario")
public class Funcionario implements Serializable{
	
	public Funcionario() {
		super();
	}
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nome;
	private Double salarioBruto;
	@Temporal(TemporalType.DATE)
	private Date dataEntrada;
	@Temporal(TemporalType.DATE)
	private Date dataSaida;
	private String cargo;
	@ManyToOne
	private Cidade cidade;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String uf;
	private String cep;
	private String email;
	private String senha;

}
