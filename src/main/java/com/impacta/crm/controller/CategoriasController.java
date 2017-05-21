package com.impacta.crm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.impacta.crm.controller.page.PageWrapper;
import com.impacta.crm.model.Categoria;
import com.impacta.crm.repository.Categorias;
import com.impacta.crm.repository.filter.CategoriaFilter;
import com.impacta.crm.service.CadastroCategoriaService;
import com.impacta.crm.service.exception.ImpossivelExcluirEntidadeException;
import com.impacta.crm.service.exception.NomeCategoriaJaCadastradoException;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {

	@Autowired
	private CadastroCategoriaService cadastroCategoriaService;
	
	@Autowired
	private Categorias categorias;
	
	@RequestMapping("/novo")
	public ModelAndView nova(Categoria categoria) {
		return new ModelAndView("categoria/CadastroCategoria");
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Categoria categoria, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return nova(categoria);
		}
		
		try {
			cadastroCategoriaService.salvar(categoria);
		} catch (NomeCategoriaJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(categoria);
		}
		
		attributes.addFlashAttribute("mensagem", "Categoria salvo com sucesso");
		return new ModelAndView("redirect:/categorias/novo");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Categoria categoria, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		categoria = cadastroCategoriaService.salvar(categoria);
		return ResponseEntity.ok(categoria);
	}
	
	@GetMapping
	public ModelAndView pesquisar(CategoriaFilter categoriaFilter, BindingResult result
			, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("categoria/PesquisaCategorias");
		
		PageWrapper<Categoria> paginaWrapper = new PageWrapper<>(categorias.filtrar(categoriaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Categoria categoria) {
		try {
			cadastroCategoriaService.excluir(categoria);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Categoria categoria) {
		ModelAndView mv = nova(categoria);
		mv.addObject(categoria);
		return mv;
	}
	
}
