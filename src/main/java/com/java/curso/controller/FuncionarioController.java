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

import com.java.curso.model.Funcionario;
import com.java.curso.repositorios.CidadeRepositorio;
import com.java.curso.repositorios.FuncionarioRepositorio;

@Controller
public class FuncionarioController {
	
	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;
	
	@Autowired
	private CidadeRepositorio cidadeRepositorio;
	
	@GetMapping("/administrativo/funcionarios/cadastrar")
	public ModelAndView cadastrar(Funcionario funcionario) {
		ModelAndView mv = new ModelAndView("administrativo/funcionarios/cadastro");
		mv.addObject("funcionario", funcionario);
		mv.addObject("listaCidades", cidadeRepositorio.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/funcionarios/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/funcionarios/lista");
		mv.addObject("listaFuncionarios", funcionarioRepositorio.findAll());
		return mv;
	}
	
	@PostMapping("/administrativo/funcionarios/salvar")
	public ModelAndView salvar(@Validated Funcionario funcionario, BindingResult result) {  
		
		System.out.println(funcionario);
		if(result.hasErrors()) {
			return cadastrar(funcionario);
		}

		//Avaliar a questão do Editar, se irá encryptar a senha duas vezes;
		funcionario.setSenha(new BCryptPasswordEncoder().encode(funcionario.getSenha()));
		funcionarioRepositorio.save(funcionario);
		return cadastrar(new Funcionario());
	}
	
	@GetMapping("/administrativo/funcionarios/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Funcionario> funcionario = funcionarioRepositorio.findById(id);	//Busca o objeto no banco;	
		funcionarioRepositorio.delete(funcionario.get());
		return listar();
	}	
	
	@GetMapping("/administrativo/funcionarios/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Funcionario> funcionario = funcionarioRepositorio.findById(id);		
		return cadastrar(funcionario.get());
	}	
}
