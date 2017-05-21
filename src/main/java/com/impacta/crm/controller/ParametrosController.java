package com.impacta.crm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.impacta.crm.model.Parametro;
import com.impacta.crm.repository.Parametros;
import com.impacta.crm.service.CadastroParametroService;

@Controller
@RequestMapping("/parametros")
public class ParametrosController {

	@Autowired
	private CadastroParametroService cadastroParametroService;
	
	@Autowired
	private Parametros parametros;
	
	@RequestMapping
	public ModelAndView novo(Parametro parametro) {
		return new ModelAndView("parametro/CadastroParametro");
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Parametro parametro, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(parametro);
		}
		
		cadastroParametroService.salvar(parametro);
	
		attributes.addFlashAttribute("mensagem", "Parametro salvo com sucesso");
		return new ModelAndView("redirect:/parametros");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Parametro parametro, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("host").getDefaultMessage());
		}
		
		parametro = cadastroParametroService.salvar(parametro);
		return ResponseEntity.ok(parametro);
	}
	
	@GetMapping
	public ModelAndView editar() {
		Parametro parametro = parametros.getOne((long) 1);
		ModelAndView mv = novo(parametro);
		mv.addObject(parametro);
		return mv;
	}
	
}
