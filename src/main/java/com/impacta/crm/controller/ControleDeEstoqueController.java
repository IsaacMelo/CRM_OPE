package com.impacta.crm.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.impacta.crm.model.Produto;
import com.impacta.crm.model.Registro;
import com.impacta.crm.model.RegistroEstoque;
import com.impacta.crm.repository.Produtos;
import com.impacta.crm.service.CadastroRegistroService;
import com.impacta.crm.service.exception.ItensEstqueObrigatorioException;
import com.impacta.crm.session.TabelaItensEstoqueSession;

@Controller
@RequestMapping("controleEstoque/")
public class ControleDeEstoqueController {
	
	@Autowired
	private TabelaItensEstoqueSession tabelaItens;
	
	@Autowired
	private Produtos produtos;
	
	@Autowired
	private CadastroRegistroService cadastroRegistroService;
	

	@RequestMapping("/novo")
	public ModelAndView nova(RegistroEstoque registro) {
		ModelAndView mv = new ModelAndView("estoque/Entrada&Saida");
		
		setUuid(registro);
		
		mv.addObject("registros", Registro.values());
		mv.addObject("totalItens", registro.getTotalItens());
		mv.addObject("totalProdutos", registro.getTotalProdutos());
		mv.addObject("tipoRegistro", registro.getRegistro());
		mv.addObject("itens", registro.getItens());
		
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid RegistroEstoque registro, BindingResult result, RedirectAttributes attributes) {
		registro.adicionarItens(tabelaItens.getItens(registro.getUuid()));
		registro.setTotalItens(tabelaItens.getTotalItens(registro.getUuid()));
		registro.setTotalProdutos(tabelaItens.getTotalProdutos(registro.getUuid()));
		if (result.hasErrors()) {
			return nova(registro);
		}
		
		try{
			if(registro.getRegistro() == Registro.ENTRADA){
				attributes.addFlashAttribute("mensagem", registro.getRegistro().getDescricao()+" salva com sucesso");
				cadastroRegistroService.salvarComoEntrada(registro);
			}else if(registro.getRegistro() == Registro.SAIDA){
				attributes.addFlashAttribute("mensagem", registro.getRegistro().getDescricao()+" salva com sucesso");
				cadastroRegistroService.salvarComoSaida(registro);
			}
		}catch(ItensEstqueObrigatorioException e){
			result.reject("", e.getMessage());
		}
		
		if (result.hasErrors()) {
			return nova(registro);
		}
		
		return new ModelAndView("redirect:/controleEstoque/novo");
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Produto produto,RegistroEstoque venda, Long codigoCerveja,BindingResult result, String uuid) {
		produto = produtos.findOne(codigoCerveja);
		tabelaItens.adicionarItem(uuid, produto, 1);
		return mvTabelaItensVenda(uuid);
	}
	
	@PutMapping("/item/{codigoCerveja}")
	public ModelAndView alterarQuantidadeItem(@PathVariable("codigoCerveja") Produto produto
			, Integer quantidade, String uuid) {
		tabelaItens.alterarQuantidadeItens(uuid, produto, quantidade);
		return mvTabelaItensVenda(uuid);
	}
	
	@DeleteMapping("/item/{uuid}/{codigoCerveja}")
	public ModelAndView excluirItem(@PathVariable("codigoCerveja") Produto produto
			, @PathVariable String uuid) {
		tabelaItens.excluirItem(uuid, produto);
		return mvTabelaItensVenda(uuid);
	}
	
	private void setUuid(RegistroEstoque registro) {
		if (StringUtils.isEmpty(registro.getUuid())) {
			registro.setUuid(UUID.randomUUID().toString());
		}
	}
	
	private ModelAndView mvTabelaItensVenda(String uuid) {
		ModelAndView mv = new ModelAndView("estoque/TabelaItensEstoque");
		mv.addObject("itens", tabelaItens.getItens(uuid));
		System.out.println(tabelaItens.getTotalItens(uuid));
		mv.addObject("totalItens", tabelaItens.getTotalItens(uuid));
		mv.addObject("totalProdutos", tabelaItens.getTotalProdutos(uuid));
		return mv;
	}
	
}
