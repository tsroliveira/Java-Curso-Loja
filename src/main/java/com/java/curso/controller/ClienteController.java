package com.java.curso.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.curso.model.Cliente;
import com.java.curso.repositorios.CidadeRepositorio;
import com.java.curso.repositorios.ClienteRepositorio;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	@Autowired
	private CidadeRepositorio cidadeRepositorio;
	
	@GetMapping("/cliente/cadastrar")
	public ModelAndView cadastrar(Cliente cliente) {
		ModelAndView mv = new ModelAndView("cliente/cadastrar");
		mv.addObject("cliente", cliente);
		mv.addObject("listaCidades", cidadeRepositorio.findAll());
		return mv;
	}
	
	
	/*
	 * @GetMapping("/clientes/listar") public ModelAndView listar() { ModelAndView
	 * mv = new ModelAndView("/cliente/lista"); mv.addObject("listaClientes",
	 * clienteRepositorio.findAll()); return mv; }
	 */
	
	
	@GetMapping("/cliente/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Cliente> cliente = clienteRepositorio.findById(id);		
		return cadastrar(cliente.get());
	}	
	
	@PostMapping("/cliente/salvar")
	public ModelAndView salvar(@Validated Cliente cliente, BindingResult result) {  
		
		if(result.hasErrors()) {
			return cadastrar(cliente);
		}
		cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
		clienteRepositorio.saveAndFlush(cliente);
		
		return cadastrar(new Cliente());
	}
	
	/*
	 * @GetMapping("/administrativo/clientes/remover/{id}") public ModelAndView
	 * remover(@PathVariable("id") Long id) { Optional<Cliente> cliente =
	 * clienteRepositorio.findById(id); //Busca o objeto no banco;
	 * clienteRepositorio.delete(cliente.get()); return listar(); }
	 */	
	

}
