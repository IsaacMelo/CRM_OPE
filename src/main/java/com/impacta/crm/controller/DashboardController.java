package com.impacta.crm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.impacta.crm.repository.Produtos;
import com.impacta.crm.repository.Clientes;
import com.impacta.crm.repository.Vendas;
import com.impacta.crm.repository.Parametros;
import com.impacta.crm.model.Parametro;

@Controller
public class DashboardController {

	@Autowired
	private Vendas vendas;
	
	@Autowired
	private Produtos produtos;
	
	@Autowired
	private Parametros parametros;
	
	private Parametro parametro;
	
	@Autowired
	private Clientes clientes;
	
	@GetMapping("/")
	public ModelAndView dashboard(HttpSession session) {
		ModelAndView mv = new ModelAndView("Dashboard");
		
		parametro = parametros.getOne((long)1);
		
		session.setAttribute("comissao", parametro.getComissao());
		session.setAttribute("desconto", parametro.getDesconto());
		session.setAttribute("margemProduto", parametro.getMargemProduto());
		session.setAttribute("nomeFantasia", parametro.getNome());
		
		mv.addObject("vendasNoAno", vendas.valorTotalNoAno());
		mv.addObject("vendasNoMes", vendas.valorTotalNoMes());
		mv.addObject("ticketMedio", vendas.valorTicketMedioNoAno());
		
		mv.addObject("valorItensEstoque", produtos.valorItensEstoque());
		mv.addObject("totalClientes", clientes.count());
		
		return mv;
	}
	
}
