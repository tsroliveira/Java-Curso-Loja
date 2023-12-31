package com.java.curso.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.java.curso.model.EntradaItens;
import com.java.curso.model.EntradaProduto;
import com.java.curso.model.Produto;
import com.java.curso.repositorios.EntradaItensRepositorio;
import com.java.curso.repositorios.EntradaProdutoRepositorio;
import com.java.curso.repositorios.FuncionarioRepositorio;
import com.java.curso.repositorios.ProdutoRepositorio;

@Controller
public class EntradaProdutoController {
	
	private List<EntradaItens> listaEntrada = new ArrayList<EntradaItens>();
	
	@Autowired
	private EntradaProdutoRepositorio entradaProdutoRepositorio;

	@Autowired
	private EntradaItensRepositorio entradaItensRepositorio;
	
	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;
	
	@Autowired
	private ProdutoRepositorio produtoRepositorio;

	@GetMapping("/administrativo/entrada/cadastrar")
	public ModelAndView cadastrar( EntradaProduto entrada, EntradaItens entradaItens) {
		ModelAndView mv = new ModelAndView("administrativo/entrada/cadastro");
		mv.addObject("entrada", entrada);
		mv.addObject("listaEntradaItens", this.listaEntrada);
		mv.addObject("entradaItens", entradaItens);
		mv.addObject("listaFuncionarios", funcionarioRepositorio.findAll());
		mv.addObject("listaProdutos", produtoRepositorio.findAll());
		return mv;
	}
	
	@GetMapping("/administrativo/entrada/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("administrativo/entrada/lista");
		mv.addObject("listaEntradaProdutos", entradaProdutoRepositorio.findAll());
		mv.addObject("listaEntradaItens", entradaItensRepositorio.findAll());
		return mv;
	}
	
	@PostMapping("/administrativo/entrada/salvar")
	public ModelAndView salvar(String acao, EntradaProduto entrada, EntradaItens entradaItens) {  
		
		if(acao.equals("itens")) {
			this.listaEntrada.add(entradaItens);
		}
		else if(acao.equals("salvar")) {
			entradaProdutoRepositorio.saveAndFlush(entrada);
			for(EntradaItens it:listaEntrada) {
				it.setEntrada(entrada);
				entradaItensRepositorio.saveAndFlush(it);
				Optional<Produto> prod = produtoRepositorio.findById(it.getProduto().getId());	//Optional verifica se o objeto existe no banco;	
				Produto produto = prod.get();
				produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + it.getQuantidade());
				produto.setValorVenda(it.getValorVenda());
				produtoRepositorio.saveAndFlush(produto);
				this.listaEntrada = new ArrayList<>();
			}
			return cadastrar(new EntradaProduto(), new EntradaItens());
		}
		
		System.out.println(this.listaEntrada.size());
		return cadastrar(entrada, new EntradaItens());
	}
	
//	@GetMapping("/administrativo/estados/remover/{id}")
//	public ModelAndView remover(@PathVariable("id") Long id) {
//		Optional<Estado> estado = estadoRepositorio.findById(id);	//Busca o objeto no banco;	
//		estadoRepositorio.delete(estado.get());
//		return listar();
//	}	
	
//	@GetMapping("/administrativo/estados/editar/{id}")
//	public ModelAndView editar(@PathVariable("id") Long id) {
//		Optional<Estado> estado = estadoRepositorio.findById(id);		
//		return cadastrar(estado.get());
//	}	
}
