package com.impacta.crm.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

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
import com.impacta.crm.repository.Produtos;
import com.impacta.crm.repository.Categorias;
import com.impacta.crm.repository.filter.ProdutoFilter;
import com.impacta.crm.service.CadastroProdutoService;
import com.impacta.crm.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private Categorias categorias;
	
	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@Autowired
	private Produtos produtos;

	@RequestMapping("/nova")
	public ModelAndView nova(Produto produto, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("produto/CadastroProduto");
			
			BigDecimal margemLucro 	= (BigDecimal) request.getSession().getAttribute("margemProduto");
			BigDecimal comissao 	= (BigDecimal) request.getSession().getAttribute("comissao");
			
		
			if(produto.isNova()){
				produto.setComissao(comissao);
			}else{
				
				BigDecimal valorSugerido = produto.getValorCompra()
						.multiply(Optional.ofNullable(margemLucro).orElse(BigDecimal.ZERO))
						.divide(new BigDecimal(100))
						.add(Optional.ofNullable(produto.getValorCompra()).orElse(BigDecimal.ZERO));
						
				DecimalFormat vlrSugeridoFN = new DecimalFormat("#,###.00");
				String valorSugeridoFN = vlrSugeridoFN.format (valorSugerido);
				
				mv.addObject("valorSugerido",valorSugeridoFN);
			}
			
			mv.addObject("margemProduto", margemLucro);
			mv.addObject("categorias", categorias.findAll());
		return mv;
	}
	
	@RequestMapping(value = { "/nova", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Produto produto, BindingResult result, Model model, RedirectAttributes attributes, HttpServletRequest request) {
		if (result.hasErrors()) {
			return nova(produto,request);
		}
		
		cadastroProdutoService.salvar(produto);
		attributes.addFlashAttribute("mensagem", "Produto salva com sucesso!");
		return new ModelAndView("redirect:/produtos/nova");
	}
	
	@GetMapping
	public ModelAndView pesquisar(ProdutoFilter produtoFilter, BindingResult result
			, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("produto/PesquisaProdutos");
		mv.addObject("categorias", categorias.findAll());
		
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
	public ModelAndView editar(@PathVariable("codigo") Produto produto, HttpServletRequest request) {
		ModelAndView mv = nova(produto, request);
		mv.addObject(produto);
		return mv;
	}
	
}
