package com.java.curso.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.curso.model.Cliente;
import com.java.curso.model.Compra;
import com.java.curso.model.ItensCompra;
import com.java.curso.model.Produto;
import com.java.curso.repositorios.ClienteRepositorio;
import com.java.curso.repositorios.CompraRepositorio;
import com.java.curso.repositorios.ItensCompraRepositorio;
import com.java.curso.repositorios.ProdutoRepositorio;

@Controller
public class CarrinhoController {

	private List<ItensCompra> itensCompra = new ArrayList<ItensCompra>();
	private Compra compra = new Compra();
	private Cliente cliente;
	private String message = "";

	@Autowired
	private ProdutoRepositorio produtoRepositorio;

	@Autowired
	private ClienteRepositorio clienteRepositorio;

	@Autowired
	private CompraRepositorio compraRepositorio;

	@Autowired
	private ItensCompraRepositorio itensRepositorio;

	private void calcularTotal() {
		compra.setValorTotal(0.0);
		for (ItensCompra it : itensCompra) {
			compra.setValorTotal(compra.getValorTotal() + it.getValorTotal());
		}
	}

	@GetMapping("/carrinho")
	public ModelAndView chamarCarrinho() {
		ModelAndView mv = new ModelAndView("cliente/carrinho");
		calcularTotal();
		mv.addObject("compra", compra);
		mv.addObject("listaItens", itensCompra);
		mv.addObject("message", message);
		message = "";
		return mv;
	}

	private void buscaUsuarioLogado() {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		if (!(autenticado instanceof AnonymousAuthenticationToken)) { // Verifica se diferente de anonimo;
			String email = autenticado.getName();
			cliente = clienteRepositorio.buscarClienteEmail(email).get(0);

		}
	}

	@GetMapping("/finalizar")
	public ModelAndView finalizarCompra() {
		buscaUsuarioLogado();
		ModelAndView mv = new ModelAndView("cliente/finalizar");
		calcularTotal();
		mv.addObject("compra", compra);
		mv.addObject("listaItens", itensCompra);
		mv.addObject("cliente", cliente);
		mv.addObject("message", message);
				
		if (itensCompra.size() == 0) {
			message = "Para finalizar uma compra é preciso selecionar um produto.";
			return chamarCarrinho();
		}
		else {
			return mv;
		}
	}

	@GetMapping("/finalizar/confirmar")
	public ModelAndView confirmarCompraRedirect() {
		message = "Não existem itens no carrinho de compras!";
		return chamarCarrinho();
	}
	
	@PostMapping("/finalizar/confirmar")
	public ModelAndView confirmarCompra(String formaPagamento) {
		ModelAndView mv = new ModelAndView("cliente/compraFinalizada");

		if (itensCompra.size() == 0) {
			message = "Não existem itens no carrinho de compras!";
			return chamarCarrinho();
		}
		
		compra.setCliente(cliente);
		compra.setFormaPagamento(formaPagamento);
		compraRepositorio.saveAndFlush(compra);

		for (ItensCompra c : itensCompra) {
			c.setCompra(compra);
			itensRepositorio.saveAndFlush(c);
		}

		mv.addObject("compra", compra);
		mv.addObject("listaItens", itensCompra);
		mv.addObject("cliente", cliente);

		message = "Compra realizada com sucesso!";
		mv.addObject("message", message);
		message = "";
		
		compra = new Compra();
		itensCompra = new ArrayList<>();
		
		return mv;
	}

	@GetMapping("/alterarQuantidade/{id}/{acao}")
	public String alterarQuantidade(@PathVariable Long id, @PathVariable Integer acao) {

		for (ItensCompra it : itensCompra) {
			if (it.getProduto().getId().equals(id)) {
				if (acao.equals(1)) {
					it.setQuantidade(it.getQuantidade() + 1);
					it.setValorTotal(0.);
					it.setValorTotal(it.getValorTotal() + (it.getQuantidade() * it.getValorUnitario()));
				} else if (acao.equals(0)) {
					it.setQuantidade(it.getQuantidade() - 1);
					it.setValorTotal(0.);
					it.setValorTotal(it.getValorTotal() + (it.getQuantidade() * it.getValorUnitario()));
				}
				break;
			}
		}

		return "redirect:/carrinho";
	}

	@GetMapping("/removerProduto/{id}")
	public String removerProdutoCarrinho(@PathVariable Long id) {

		for (ItensCompra it : itensCompra) {
			if (it.getProduto().getId().equals(id)) {
				itensCompra.remove(it);
				break;
			}
		}

		return "redirect:/carrinho";
	}

	@GetMapping("/adicionarCarrinho/{id}")
	public String adicionarCarrinho(@PathVariable Long id) {

		Optional<Produto> prod = produtoRepositorio.findById(id);
		Produto produto = prod.get();

		int controle = 0;
		for (ItensCompra it : itensCompra) {
			if (it.getProduto().getId().equals(produto.getId())) {
				it.setQuantidade(it.getQuantidade() + 1);
				it.setValorTotal(0.);
				it.setValorTotal(it.getValorTotal() + (it.getQuantidade() * it.getValorUnitario()));
				controle = 1;
				break;
			}
		}

		if (controle == 0) {
			ItensCompra item = new ItensCompra();
			item.setProduto(produto);
			item.setValorUnitario(produto.getValorVenda());
			item.setQuantidade(item.getQuantidade() + 1);
			item.setValorTotal(item.getValorTotal() + (item.getQuantidade() * item.getValorUnitario()));
			itensCompra.add(item);
		}

		return "redirect:/carrinho";
	}
}
