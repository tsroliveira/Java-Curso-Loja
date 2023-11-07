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

import com.java.curso.model.Cidade;
import com.java.curso.repositorios.CidadeRepositorio;
import com.java.curso.repositorios.EstadoRepositorio;

@Controller
public class CidadeController {
	
	@Autowired
	private CidadeRepositorio cidadeRepositorio;
	
	@Autowired
	private EstadoRepositorio estadoRepositorio;
	
	@GetMapping("/administrativo/cidades/cadastrar")
	public ModelAndView cadastrar(Cidade cidade) {
		ModelAndView mv = new ModelAndView("administrativo/cidades/cadastro");
		mv.addObject("cidade", cidade);
		mv.addObject("listaEstados", estadoRepositorio.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/cidades/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/cidades/lista");
		mv.addObject("listaCidades", cidadeRepositorio.findAll());
		return mv;
	}
	
	@PostMapping("/administrativo/cidades/salvar")
	public ModelAndView salvar(@Validated Cidade cidade, BindingResult result) {  
		
		System.out.println(cidade);
		if(result.hasErrors()) {
			return cadastrar(cidade);
		}
		
		cidadeRepositorio.save(cidade);
		return cadastrar(new Cidade());
	}
	
	@GetMapping("/administrativo/cidades/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Cidade> cidade = cidadeRepositorio.findById(id);	//Busca o objeto no banco;	
		cidadeRepositorio.delete(cidade.get());
		return listar();
	}	
	
	@GetMapping("/administrativo/cidades/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Cidade> cidade = cidadeRepositorio.findById(id);		
		return cadastrar(cidade.get());
	}	
}
