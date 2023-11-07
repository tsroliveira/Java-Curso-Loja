package com.java.curso.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.sound.midi.Patch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.java.curso.model.Produto;
import com.java.curso.repositorios.ProdutoRepositorio;

@Controller
public class ProdutoController {
	
	private static String caminhoImagens = "C:\\Users\\thiag\\Downloads\\Workspace\\Java_Trilha\\Java-Curso-Loja\\src\\main\\resources\\static\\img_produtos\\";
	
	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	
	@GetMapping("/administrativo/produtos/cadastrar")
	public ModelAndView cadastrar(Produto produto) {
		ModelAndView mv = new ModelAndView("administrativo/produtos/cadastro");
		mv.addObject("produto", produto);
		return mv;
	}
	
	@GetMapping("/administrativo/produtos/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/produtos/lista");
		mv.addObject("listaProdutos", produtoRepositorio.findAll());
		return mv;
	}
	
	@PostMapping("/administrativo/produtos/salvar")
	public ModelAndView salvar(@Validated Produto produto, BindingResult result, 
			@RequestParam("file") MultipartFile arquivo) {  
		
		System.out.println(produto);
		System.out.println("\nTamanho do Arquivo = " + String.valueOf(arquivo.getSize()));
		
		if(result.hasErrors()) {
			return cadastrar(produto);
		}
		
		//Salvamos o produto primeiro para abaixo utilizarmos o ID dele para concatenar com o nome da imagem;
		produtoRepositorio.saveAndFlush(produto);
		
		try {
			if(!arquivo.isEmpty()) {
				//Salvando a imagem no diretorio do servidor;
				byte[] bytes = arquivo.getBytes();
				Path caminho = Paths.get(caminhoImagens + String.valueOf(produto.getId()) + arquivo.getOriginalFilename());
				Files.write(caminho, bytes);
				
				//Salvamos somente o nome do arquivo no banco;
				produto.setNomeImagem(String.valueOf(produto.getId()) + arquivo.getOriginalFilename());
				produtoRepositorio.saveAndFlush(produto);	//Atualiza o cadastro do produto, agora com a imagem;
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return cadastrar(new Produto());
	}
	
	@GetMapping("/administrativo/produtos/remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id) {
		Optional<Produto> produto = produtoRepositorio.findById(id);	//Busca o objeto no banco;	
		produtoRepositorio.delete(produto.get());
		return listar();
	}	
	
	@GetMapping("/administrativo/produtos/mostrarImagem/{imagem}")
	@ResponseBody
	public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
		
		File imagemArquivo = new File(caminhoImagens + imagem);
		
		if(imagem != null || imagem.trim().length()>0) {
			return Files.readAllBytes(imagemArquivo.toPath());
		}
		return null;
	}	
	
	@GetMapping("/administrativo/produtos/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Produto> produto = produtoRepositorio.findById(id);		
		return cadastrar(produto.get());
	}	
}
