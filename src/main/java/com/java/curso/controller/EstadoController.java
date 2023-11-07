package com.java.curso.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.curso.model.Estado;
import com.java.curso.repositorios.EstadoRepositorio;

@Controller
public class EstadoController {
	
	@Autowired
	private EstadoRepositorio estadoRepositorio;
	
	@GetMapping("/administrativo/estados/cadastrar")
	public ModelAndView cadastrar(Estado estado) {
		ModelAndView mv = new ModelAndView("administrativo/estados/cadastro");
		mv.addObject("estado", estado);
		return mv;
	}
	
	@GetMapping("/administrativo/estados/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/estados/lista");
		mv.addObject("listaEstados", estadoRepositorio.findAll());
		return mv;
	}
	
	@PostMapping("/administrativo/estados/salvar")
	public ModelAndView salvar(@Validated Estado estado, BindingResult result) {  
		
		System.out.println(estado);
		if(result.hasErrors()) {
			return cadastrar(estado);
		}
		
		estadoRepositorio.save(estado);
		return cadastrar(new Estado());
	}
	
	@GetMapping("/administrativo/estados/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Estado> estado = estadoRepositorio.findById(id);	//Busca o objeto no banco;	
		estadoRepositorio.delete(estado.get());
		return listar();
	}	
	
	@GetMapping("/administrativo/estados/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Estado> estado = estadoRepositorio.findById(id);		
		return cadastrar(estado.get());
	}	
}
