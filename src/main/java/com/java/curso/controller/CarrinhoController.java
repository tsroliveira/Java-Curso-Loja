package com.java.curso.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.java.curso.model.ItensCompra;
import com.java.curso.model.Produto;
import com.java.curso.repositorios.ProdutoRepositorio;

@Controller
public class CarrinhoController {
		
	private List<ItensCompra> itensCompra = new ArrayList<ItensCompra>();

	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	
	@GetMapping("/carrinho")
	public ModelAndView chamarCarrinho() {
		ModelAndView mv = new ModelAndView("cliente/carrinho");
		mv.addObject("listaProdutos", itensCompra);
		return mv;
	}	
	
	@GetMapping("/alterarQuantidade/{id}/{acao}")
	public ModelAndView alterarQuantidade(@PathVariable Long id, @PathVariable Integer acao) {
		ModelAndView mv = new ModelAndView("cliente/carrinho");
		
		for (ItensCompra it:itensCompra) {
			if (it.getProduto().getId().equals(id)) {
				if (acao.equals(1)) {
					it.setQuantidade(it.getQuantidade() + 1);
				}
				else if (acao.equals(0)) {
					it.setQuantidade(it.getQuantidade() - 1);
				}
				break;
			}
		}
		
		mv.addObject("listaItens", itensCompra);
		return mv;
	}	
	
	@GetMapping("/removerProduto/{id}")
	public ModelAndView removerProdutoCarrinho(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("cliente/carrinho");
		
		for (ItensCompra it:itensCompra) {
			if (it.getProduto().getId().equals(id)) {
				itensCompra.remove(it);
				break;
			}
		}
		
		mv.addObject("listaItens", itensCompra);
		return mv;
	}	
	
	@GetMapping("/adicionarCarrinho/{id}")
	public ModelAndView adicionarCarrinho(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("cliente/carrinho");
		
		Optional<Produto> prod = produtoRepositorio.findById(id);
		Produto produto = prod.get();
		
		int controle = 0;
		for (ItensCompra it:itensCompra) {
			if (it.getProduto().getId().equals(produto.getId())) {
				it.setQuantidade(it.getQuantidade() + 1);
				controle = 1;
				break;
			}
		}
		
		if (controle == 0) {
			ItensCompra item = new ItensCompra();
			item.setProduto(produto);
			item.setValorUnitario(produto.getValorVenda());
			item.setQuantidade(item.getQuantidade()+1);
			item.setValorTotal(item.getQuantidade()*item.getValorUnitario());
			itensCompra.add(item);
		}

		mv.addObject("listaItens", itensCompra);
		return mv;
	}
}
