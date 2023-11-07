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
import com.java.curso.model.Perfil;
import com.java.curso.repositorios.PerfilRepositorio;

@Controller
public class PerfilController {
	
	@Autowired
	private PerfilRepositorio perfilRepositorio;
	
	@GetMapping("/administrativo/perfis/cadastrar")
	public ModelAndView cadastrar(Perfil perfil) {
		ModelAndView mv = new ModelAndView("administrativo/perfis/cadastro");
		mv.addObject("perfil", perfil);
		return mv;
	}
	
	@GetMapping("/administrativo/perfis/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/perfis/lista");
		mv.addObject("listaPerfis", perfilRepositorio.findAll());
		return mv;
	}
	
	@PostMapping("/administrativo/perfis/salvar")
	public ModelAndView salvar(@Validated Perfil perfil, BindingResult result) {  
		
		System.out.println(perfil);
		if(result.hasErrors()) {
			return cadastrar(perfil);
		}
		
		perfilRepositorio.save(perfil);
		return cadastrar(new Perfil());
	}
	
	@GetMapping("/administrativo/perfis/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Perfil> perfil = perfilRepositorio.findById(id);	//Busca o objeto no banco;	
		perfilRepositorio.delete(perfil.get());
		return listar();
	}	
	
	@GetMapping("/administrativo/perfis/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Perfil> perfil = perfilRepositorio.findById(id);		
		return cadastrar(perfil.get());
	}	
}
