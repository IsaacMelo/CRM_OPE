package com.impacta.crm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.impacta.crm.controller.page.PageWrapper;
import com.impacta.crm.dto.ProdutoDTO;
import com.impacta.crm.model.Produto;
import com.impacta.crm.model.Origem;
import com.impacta.crm.model.Sabor;
import com.impacta.crm.repository.Produtos;
import com.impacta.crm.repository.Estilos;
import com.impacta.crm.repository.filter.ProdutoFilter;
import com.impacta.crm.service.CadastroProdutoService;
import com.impacta.crm.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private Estilos estilos;
	
	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@Autowired
	private Produtos produtos;

	@RequestMapping("/nova")
	public ModelAndView nova(Produto produto) {
		ModelAndView mv = new ModelAndView("produto/CadastroProduto");
		mv.addObject("sabores", Sabor.values());
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("origens", Origem.values());
		return mv;
	}
	
	@RequestMapping(value = { "/nova", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Produto produto, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return nova(produto);
		}
		
		cadastroProdutoService.salvar(produto);
		attributes.addFlashAttribute("mensagem", "Produto salva com sucesso!");
		return new ModelAndView("redirect:/produtos/nova");
	}
	
	@GetMapping
	public ModelAndView pesquisar(ProdutoFilter produtoFilter, BindingResult result
			, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("produto/PesquisaProdutos");
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("sabores", Sabor.values());
		mv.addObject("origens", Origem.values());
		
		PageWrapper<Produto> paginaWrapper = new PageWrapper<>(produtos.filtrar(produtoFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ProdutoDTO> pesquisar(String skuOuNome) {
		return produtos.porSkuOuNome(skuOuNome);
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Produto produto) {
		try {
			cadastroProdutoService.excluir(produto);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Produto produto) {
		ModelAndView mv = nova(produto);
		mv.addObject(produto);
		return mv;
	}
	
}
