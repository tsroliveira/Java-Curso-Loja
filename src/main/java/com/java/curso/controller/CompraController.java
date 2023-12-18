package com.java.curso.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.java.curso.model.Compra;
import com.java.curso.repositorios.CompraRepositorio;

@Controller
public class CompraController {
	
	private static String caminhoImagens = "C:\\Users\\thiag\\Downloads\\Workspace\\Java_Trilha\\Java-Curso-Loja\\src\\main\\resources\\static\\img_produtos\\";
	
	@Autowired
	private CompraRepositorio compraRepositorio;
	
	@GetMapping("/cliente/compras/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("cliente/compras/lista");
		mv.addObject("listaCompras", compraRepositorio.findAll());
		//mv.addObject("listaCompras", compraRepositorio.buscarComprasCliente(3));
		return mv;
	}
	
	@GetMapping("/cliente/compras/cancelar/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Compra> compra = compraRepositorio.findById(id);	//Busca o objeto no banco;	
		//compraRepositorio.delete(compra.get());
		return listar();
	}	
	
	@GetMapping("/cliente/compras/mostrarImagem/{imagem}")
	@ResponseBody
	public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
		
		File imagemArquivo = new File(caminhoImagens + imagem);
		
		if(imagem != null || imagem.trim().length()>0) {
			return Files.readAllBytes(imagemArquivo.toPath());
		}
		return null;
	}	

}
