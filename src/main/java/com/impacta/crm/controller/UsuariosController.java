package com.impacta.crm.controller;

import java.util.UUID;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.impacta.crm.controller.page.PageWrapper;
import com.impacta.crm.model.ContaBancaria;
import com.impacta.crm.model.Usuario;
import com.impacta.crm.repository.Bancos;
import com.impacta.crm.repository.Grupos;
import com.impacta.crm.repository.Usuarios;
import com.impacta.crm.repository.filter.UsuarioFilter;
import com.impacta.crm.security.UsuarioSistema;
import com.impacta.crm.service.CadastroUsuarioService;
import com.impacta.crm.service.StatusUsuario;
import com.impacta.crm.service.exception.ContaBancariaJaCadastradaException;
import com.impacta.crm.service.exception.ContaObrigatoriaUsuarioException;
import com.impacta.crm.service.exception.ContaPrincipalUsuarioException;
import com.impacta.crm.service.exception.EmailUsuarioJaCadastradoException;
import com.impacta.crm.service.exception.ImpossivelExcluirEntidadeException;
import com.impacta.crm.service.exception.SenhaObrigatoriaUsuarioException;
import com.impacta.crm.session.TabelaContaBancariaSession;


@Controller
@RequestMapping
public class UsuariosController {
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	private Grupos grupos;
	
	@Autowired
	private Bancos bancos;
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private TabelaContaBancariaSession tabelaContas;
	
	
	
	@RequestMapping("/usuarios/novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		
		if(StringUtils.isEmpty(usuario.getUuid())){
			usuario.setUuid(UUID.randomUUID().toString());
			tabelaContas.adiconarTabela(usuario.getContas(), usuario.getUuid());
		}else{
			tabelaContas.adiconarTabela(usuario.getContas(), usuario.getUuid());
		}
		
		mv.addObject("grupos", grupos.findAll());
		mv.addObject("bancos", bancos.findAll());
		mv.addObject("contas", tabelaContas.getContas(usuario.getUuid()));
		mv.addObject("principal", false);
		return mv;
	}
			
	@PostMapping({ "/usuarios/novo", "/usuarios/{\\+d}"})
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return novo(usuario);
		}
		
		usuario.adicionarContas(tabelaContas.getContas(usuario.getUuid()));
		
		try {
			cadastroUsuarioService.salvar(usuario);
		} catch (EmailUsuarioJaCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return novo(usuario);
		} catch (SenhaObrigatoriaUsuarioException e) {
			result.rejectValue("senha", e.getMessage(), e.getMessage());
			return novo(usuario);
		} catch(ContaObrigatoriaUsuarioException e){
			result.reject("", e.getMessage());
		} catch(ContaPrincipalUsuarioException e){
			result.reject("", e.getMessage());
		} 
		
		if (result.hasErrors()) {
			return novo(usuario);
		}
		
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso");
		return new ModelAndView("redirect:/usuarios/novo");
	}
	
	@GetMapping(value = "/usuarios")
	public ModelAndView pesquisar(UsuarioFilter usuarioFilter
			, @PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("/usuario/PesquisaUsuarios");
		mv.addObject("grupos", grupos.findAll());
		PageWrapper<Usuario> paginaWrapper = new PageWrapper<>(usuarios.filtrar(usuarioFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	//Pesquisa rápida
	@RequestMapping(value = "/pesquisaRapida", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Usuario> pesquisar(String nome){
		validarTamanhoNome(nome);
		List<Usuario> users = usuarios.findByNomeStartingWithIgnoreCase(nome);
		return users;
	}
	
	private void validarTamanhoNome(String nome) {new ModelAndView("redirect:/usuarios/novo");
		if (StringUtils.isEmpty(nome) || nome.length() < 3) {
			throw new IllegalArgumentException();
		}
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping("/usuarios/status")
	@ResponseStatus(HttpStatus.OK)
	public void atualizarStatus(@RequestParam("codigos[]") Long[] codigos, @RequestParam("status") StatusUsuario statusUsuario) {
		cadastroUsuarioService.alterarStatus(codigos, statusUsuario);
	}	
	
	@GetMapping("/usuarios/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Usuario usuario = usuarios.buscarComGrupos(codigo);
		ModelAndView mv = novo(usuario);
		mv.addObject(usuario);
		return mv;
	}
	
	@DeleteMapping("/usuarios/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable Long codigo) {
		Usuario usuario = usuarios.buscarComGrupos(codigo);
		try{
			cadastroUsuarioService.excluir(usuario);
		} catch(ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
			
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping("/meusDados")
	public ModelAndView editarMeusDados(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/MeusDados");
		
		if(StringUtils.isEmpty(usuario.getUuid())){
			usuario.setUuid(UUID.randomUUID().toString());
			tabelaContas.adiconarTabela(usuario.getContas(), usuario.getUuid());
		}else{
			tabelaContas.adiconarTabela(usuario.getContas(), usuario.getUuid());
		}
		
		mv.addObject("grupos", usuario.getGrupos());
		mv.addObject("bancos", bancos.findAll());
		mv.addObject("contas", tabelaContas.getContas(usuario.getUuid()));
		mv.addObject("principal", false);
		return mv;
	}
	
	@GetMapping("/meusDados/{codigo}")
	public ModelAndView meusDados(@PathVariable Long codigo, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		if(!usuarioSistema.getUsuario().getCodigo().equals(codigo)){
			return new ModelAndView("redirect:/meusDados/"+usuarioSistema.getUsuario().getCodigo());
		}
		Usuario usuario = usuarios.buscarComGrupos(codigo);
		ModelAndView mv = editarMeusDados(usuario);
		mv.addObject(usuario);
		return mv;
	}
	
	@PostMapping({"/meusDados/{\\+d}"})
	public ModelAndView salvarmMeusDados(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		
		if (result.hasErrors()) {
			return novo(usuario);
		}
		
		usuario.adicionarContas(tabelaContas.getContas(usuario.getUuid()));
		
		try {
			cadastroUsuarioService.salvar(usuario);
		} catch (EmailUsuarioJaCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return novo(usuario);
		} catch (SenhaObrigatoriaUsuarioException e) {
			result.rejectValue("senha", e.getMessage(), e.getMessage());
			return novo(usuario);
		} catch(ContaObrigatoriaUsuarioException e){
			result.reject("", e.getMessage());
		} catch(ContaPrincipalUsuarioException e){
			result.reject("", e.getMessage());
		} 
		
		if (result.hasErrors()) {
			return novo(usuario);
		}
		
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso");
		return new ModelAndView("redirect:/meusDados/"+usuarioSistema.getUsuario().getCodigo());
	}
	
	@RequestMapping(value = "/contas",method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid ContaBancaria conta, BindingResult result){
		
		if(result.hasErrors()){
			return ResponseEntity.badRequest().body(result.getFieldErrors());
		}
		
		try{
			tabelaContas.adicionarConta(conta.getUuid(), conta);
		}catch(ContaBancariaJaCadastradaException e){
			result.reject("", e.getMessage());
		}
		
		if(result.hasErrors()){
			return ResponseEntity.badRequest().body(result.getAllErrors());
		}
	
		return ResponseEntity.ok().body(tabelaContas.getContas(conta.getUuid()));
	}
	
	@RequestMapping(value = "/contas", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> editar(@RequestBody @Valid ContaBancaria conta, BindingResult result){
		
		if(result.hasErrors()){
			return ResponseEntity.badRequest().body(result.getFieldErrors());
		}
		
		try{
			tabelaContas.editarConta(conta.getUuid(), conta);
		}catch(ContaBancariaJaCadastradaException e){
			result.reject("", e.getMessage());
		}
		
		if(result.hasErrors()){
			return ResponseEntity.badRequest().body(result.getAllErrors());
		}
		
		return ResponseEntity.ok().body(tabelaContas.getContas(conta.getUuid()));
	}
	
	@DeleteMapping(value = "/contas/{uuid}/{id}/")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id")String id, @PathVariable("uuid") String uuid){
		cadastroUsuarioService.excluirConta(uuid, id);
		tabelaContas.excluirConta(uuid, id);
		return ResponseEntity.ok().body(tabelaContas.getContas(uuid));
	}
	
	
}
