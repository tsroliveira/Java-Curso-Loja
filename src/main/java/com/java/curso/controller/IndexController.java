package com.java.curso.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.curso.model.ItensCompra;
import com.java.curso.repositorios.ProdutoRepositorio;

@Controller
public class IndexController {
	
	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	
	private List<ItensCompra> itensCompra = new ArrayList<ItensCompra>();

	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("listaProdutos", produtoRepositorio.findAll());
		mv.addObject("listaItens", itensCompra);
		return mv;
	}
	
	@GetMapping("/old")
	public ModelAndView index2() {
		ModelAndView mv = new ModelAndView("/index_old");
		mv.addObject("listaProdutos", produtoRepositorio.findAll());
		mv.addObject("listaItens", itensCompra);
		return mv;
	}
	
}
