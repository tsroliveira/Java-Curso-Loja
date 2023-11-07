package com.java.curso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.curso.model.Cidade;

@Controller
public class MainController {
	
	@GetMapping("/administrativo")
	public String acessarHome() {
		return "administrativo/home";
	}

	@GetMapping("/negado")
	public ModelAndView negado(Cidade cidade) {
		ModelAndView mv = new ModelAndView("/negado");
		return mv;
	}
	
	@GetMapping("/logout")
	public ModelAndView logout() {
		ModelAndView mv = new ModelAndView("/logout");
		mv.addObject("Deslogado", "Deslogado");
		return mv;
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("/login");
		mv.addObject("Logado", "Logado");
		return mv;
	}
}
