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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.impacta.crm.controller.page.PageWrapper;
import com.impacta.crm.model.Fornecedor;
import com.impacta.crm.model.TipoPessoa;
import com.impacta.crm.repository.Fornecedores;
import com.impacta.crm.repository.filter.FornecedorFilter;
import com.impacta.crm.service.CadastroFornecedorService;
import com.impacta.crm.service.exception.CpfCnpjClienteJaCadastradoException;

@Controller
@RequestMapping("/fornecedores")
public class FornecedoresController {

	@Autowired
	private CadastroFornecedorService cadastroFornecedorService;
	
	@Autowired
	private Fornecedores fornecedores;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Fornecedor fornecedor) {
		ModelAndView mv = new ModelAndView("fornecedor/CadastroFornecedor");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo")  Fornecedor fornecedor) {
		ModelAndView mv = novo(fornecedor);
		mv.addObject(fornecedor);
		return mv;
	}
	
	@RequestMapping(value = {"/novo", "{\\d+}"},method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Fornecedor fornecedor, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(fornecedor);
		}
		
		try {
			cadastroFornecedorService.salvar(fornecedor);
		} catch (CpfCnpjClienteJaCadastradoException e) {
			result.rejectValue("cpfOuCnpj", e.getMessage(), e.getMessage());
			return novo(fornecedor);
		}
		
		attributes.addFlashAttribute("mensagem", "Fornecedor salvo com sucesso!");
		return new ModelAndView("redirect:/fornecedores/novo");
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Fornecedor forncedor){
		cadastroFornecedorService.excluir(forncedor);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ModelAndView pesquisar(FornecedorFilter fornecedorFilter, BindingResult result
			, @PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("fornecedor/PesquisaFornecedor");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		
		PageWrapper<Fornecedor> paginaWrapper = new PageWrapper<>(fornecedores.filtrar(fornecedorFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Fornecedor> pesquisar(String nome) {
		validarTamanhoNome(nome);
		return fornecedores.findByNomeStartingWithIgnoreCase(nome);
	}

	private void validarTamanhoNome(String nome) {
		if (StringUtils.isEmpty(nome) || nome.length() < 3) {
			throw new IllegalArgumentException();
		}
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}
}
