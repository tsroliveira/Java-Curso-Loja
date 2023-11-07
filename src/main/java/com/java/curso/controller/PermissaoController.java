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

import com.java.curso.model.Permissao;
import com.java.curso.repositorios.PermissaoRepositorio;
import com.java.curso.repositorios.FuncionarioRepositorio;
import com.java.curso.repositorios.PerfilRepositorio;

@Controller
public class PermissaoController {
	
	@Autowired
	private PermissaoRepositorio permissaoRepositorio;
	
	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;
	
	@Autowired
	private PerfilRepositorio perfilRepositorio;
	
	@GetMapping("/administrativo/permissoes/cadastrar")
	public ModelAndView cadastrar(Permissao permissao) {
		ModelAndView mv = new ModelAndView("administrativo/permissoes/cadastro");
		mv.addObject("permissao", permissao);
		mv.addObject("listaFuncionarios", funcionarioRepositorio.findAll());
		mv.addObject("listaPerfis", perfilRepositorio.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/permissoes/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/permissoes/lista");
		mv.addObject("listaPermissoes", permissaoRepositorio.findAll());
		return mv;
	}
	
	@PostMapping("/administrativo/permissoes/salvar")
	public ModelAndView salvar(@Validated Permissao permissao, BindingResult result) {  
		
		System.out.println(permissao);
		if(result.hasErrors()) {
			return cadastrar(permissao);
		}
		
		permissaoRepositorio.save(permissao);
		return cadastrar(new Permissao());
	}
	
	@GetMapping("/administrativo/permissoes/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Permissao> permissao = permissaoRepositorio.findById(id);	//Busca o objeto no banco;	
		permissaoRepositorio.delete(permissao.get());
		return listar();
	}	
	
	@GetMapping("/administrativo/permissoes/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Permissao> permissao = permissaoRepositorio.findById(id);		
		return cadastrar(permissao.get());
	}	
}
