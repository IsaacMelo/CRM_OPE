package com.impacta.crm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.impacta.crm.controller.page.PageWrapper;
import com.impacta.crm.controller.validator.VendaItemValidator;
import com.impacta.crm.controller.validator.VendaValidator;
import com.impacta.crm.dto.VendaCategoria;
import com.impacta.crm.dto.VendaMes;
import com.impacta.crm.mail.Mailer;
import com.impacta.crm.model.ItemVenda;
import com.impacta.crm.model.Parametro;
import com.impacta.crm.model.Produto;
import com.impacta.crm.model.StatusVenda;
import com.impacta.crm.model.TipoPessoa;
import com.impacta.crm.model.Venda;
import com.impacta.crm.repository.FormaPagamentos;
import com.impacta.crm.repository.Parametros;
import com.impacta.crm.repository.Produtos;
import com.impacta.crm.repository.Usuarios;
import com.impacta.crm.repository.Vendas;
import com.impacta.crm.repository.filter.VendaFilter;
import com.impacta.crm.security.UsuarioSistema;
import com.impacta.crm.service.CadastroVendaService;
import com.impacta.crm.session.TabelasItensSession;

@Controller
@RequestMapping("/vendas")
public class VendasController {
	
	@Autowired
	private Produtos produtos;
	
	@Autowired
	private Parametros parametros;
	
	private Parametro parametro;
	
	@Autowired
	private FormaPagamentos formaPagamentos;
	
	@Autowired
	private TabelasItensSession tabelaItens;
	
	@Autowired
	private CadastroVendaService cadastroVendaService;
	
	@Autowired
	private VendaValidator vendaValidator;
	
	@Autowired
	private VendaItemValidator vendaItemValidator;
	
	@Autowired
	private Vendas vendas;
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private Mailer mailer;
	
	@InitBinder("venda")
	public void inicializarValidador(WebDataBinder binder) {
		binder.setValidator(vendaValidator);
		binder.setValidator(vendaItemValidator);
	}
	
	@GetMapping("/nova")
	public ModelAndView nova(Venda venda) {
		ModelAndView mv = new ModelAndView("venda/CadastroVenda");
		parametro = parametros.getOne((long)1);
		
		setUuid(venda);
		venda.setBaseComissao(parametro.getComissao());
		venda.setDescontoMax(parametro.getDesconto());
		
		mv.addObject("itens", venda.getItens());
		mv.addObject("valorFrete", venda.getValorFrete());
		mv.addObject("valorDesconto", venda.getValorDesconto());
		mv.addObject("valorTotalItens", tabelaItens.getValorTotal(venda.getUuid()));
		mv.addObject("valorComissao", venda.getValorComissao());
		mv.addObject("formaPagamentos", formaPagamentos.findAll());
		mv.addObject("baseComissao", venda.getBaseComissao());
		
		return mv;
	}
	
	@PostMapping(value = "/nova", params = "salvar")
	public ModelAndView salvar(Venda venda, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		validarVenda(venda, result);
		if (result.hasErrors()) {
			return nova(venda);
		}
		
		if(venda.isNova()){
			venda.setUsuario(usuarioSistema.getUsuario());	
		}
				
		if(venda.getStatus() == StatusVenda.ORCAMENTO){
			attributes.addFlashAttribute("mensagem", "Orçamento salvo com sucesso");
			cadastroVendaService.salvar(venda);
		
		}else if(venda.getStatus() == StatusVenda.EMITIDA){
			vendaItemValidator.validate(venda, result);
			if (result.hasErrors()) {
				return nova(venda);
			}
			attributes.addFlashAttribute("mensagem", "Venda salva com sucesso");
			cadastroVendaService.emitir(venda);
		}
		
		return new ModelAndView("redirect:/vendas/nova");
	}

	@PostMapping(value = "/nova", params = "emitir")
	public ModelAndView emitir(Venda venda, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		validarVenda(venda, result);
		vendaItemValidator.validate(venda, result);
		if (result.hasErrors()) {
			return nova(venda);
		}
		
		if(venda.isNova()){
			venda.setUsuario(usuarioSistema.getUsuario());	
		}
		
		cadastroVendaService.emitir(venda);

		attributes.addFlashAttribute("mensagem", String.format("Venda salva com sucesso", venda.getCodigo()));
		return new ModelAndView("redirect:/vendas/nova");
	}
	
	@PostMapping(value = "/nova", params = "enviarEmail")
	public ModelAndView enviarEmail(Venda venda, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema, HttpServletRequest request) {
		validarVenda(venda, result);
		if (result.hasErrors()) {
			return nova(venda);
		}
		
		if(venda.isNova()){
			venda.setUsuario(usuarioSistema.getUsuario());	
		}
		
		if(venda.getStatus() == StatusVenda.ORCAMENTO){
			attributes.addFlashAttribute("mensagem", String.format("Orçamento salvo com sucesso e e-mail enviado"));
			cadastroVendaService.salvar(venda);
		
		}else if(venda.getStatus() == StatusVenda.EMITIDA){
			vendaItemValidator.validate(venda, result);
			if (result.hasErrors()) {
				return nova(venda);
			}
			attributes.addFlashAttribute("mensagem", String.format("Venda salva com sucesso e e-mail enviado"));
			cadastroVendaService.emitir(venda);
		}
		
		mailer.enviar(venda, request);
				
		return new ModelAndView("redirect:/vendas/nova");
	}
	
	@PostMapping(value = "/nova", params = "cancelar")
	public ModelAndView cancelar(Venda venda, BindingResult result
				, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		try {
			cadastroVendaService.cancelar(venda);
		} catch (AccessDeniedException e) {
			return new ModelAndView("/403");
		}
		
		attributes.addFlashAttribute("mensagem", "Venda cancelada com sucesso");
		return new ModelAndView("redirect:/vendas/" + venda.getCodigo());
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Produto produto,Venda venda, Long codigoCerveja,BindingResult result, String uuid) {
		produto = produtos.findOne(codigoCerveja);
		tabelaItens.adicionarItem(uuid, 0, produto, 1);
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
	
	@GetMapping
	public ModelAndView pesquisar(VendaFilter vendaFilter,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		ModelAndView mv = new ModelAndView("/venda/PesquisaVendas");
				
		vendaFilter.setRoles(usuarioSistema.getAuthorities());
		vendaFilter.setCodigoUsuario(usuarioSistema.getUsuario().getCodigo());
		
		mv.addObject("todosUsuarios", usuarios.findByAtivoEquals(true));
		mv.addObject("todosStatus", StatusVenda.values());
		mv.addObject("tiposPessoa", TipoPessoa.values());
		
		PageWrapper<Venda> paginaWrapper = new PageWrapper<>(vendas.filtrar(vendaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@GetMapping("/faturadas")
	public ModelAndView pesquisarFaturadas(VendaFilter vendaFilter,
			@PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("/venda/PesquisaVendasFaturadas");

		mv.addObject("todosUsuarios", usuarios.findByAtivoEquals(true));
		mv.addObject("todosStatus", findStatusVenda());
		mv.addObject("tiposPessoa", TipoPessoa.values());
		
		PageWrapper<Venda> paginaWrapper = new PageWrapper<>(vendas.filtrarFaturada(vendaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		Venda venda = vendas.buscarComItens(codigo);
		
		if(!verificarPermissao(usuarioSistema)){
			if(!venda.getUsuario().getCodigo().equals(usuarioSistema.getUsuario().getCodigo())){
				return new ModelAndView("/403");
			}
		}
		
		if(venda.getDataHoraEntrega() != null){
			venda.setDataEntrega(venda.getDataHoraEntrega().toLocalDate());
			venda.setHorarioEntrega(venda.getDataHoraEntrega().toLocalTime());
		}
		
		setUuid(venda);
		for (ItemVenda item : venda.getItens()) {
			tabelaItens.adicionarItem(venda.getUuid(), item.getCodigo(), item.getProduto(), item.getQuantidade());
		}
		
		ModelAndView mv = nova(venda);
		mv.addObject(venda);
		return mv;
	}
	
	@GetMapping("/transporte/{codigo}")
	public ModelAndView transporte(@PathVariable("codigo") Venda venda){
		cadastroVendaService.tranporte(venda);
		return new ModelAndView("redirect:/vendas/faturadas");
	}
	
	@GetMapping("/finalizar/{codigo}")
	public ModelAndView finalizarVenda(@PathVariable("codigo") Venda venda){
		cadastroVendaService.finalizar(venda);
		return new ModelAndView("redirect:/vendas/faturadas");
	}
	
	@GetMapping("/faturar/{codigo}")
	public ModelAndView faturarVenda(@PathVariable("codigo") Venda venda){
		cadastroVendaService.faturar(venda);
		return new ModelAndView("redirect:/vendas");
	}	
	
	@GetMapping("/totalPorMes")
	public @ResponseBody List<VendaMes> listarTotalVendaPorMes() {
		return vendas.totalPorMes();
	}
	
	@GetMapping("/porCategoria")
	public @ResponseBody List<VendaCategoria> vendasPorCategoria() {
		return this.vendas.totalPorCategoria();
	}
	
	private ModelAndView mvTabelaItensVenda(String uuid) {
		ModelAndView mv = new ModelAndView("venda/TabelaItensVenda");
		mv.addObject("itens", tabelaItens.getItens(uuid));
		mv.addObject("valorTotal", tabelaItens.getValorTotal(uuid));
		return mv;
	}
	
	private void validarVenda(Venda venda, BindingResult result) {
		venda.adicionarItens(tabelaItens.getItens(venda.getUuid()));
		venda.adicionarItensAlterados(tabelaItens.getItensAlterados(venda.getUuid()));
		venda.adicionarItensDeletados(tabelaItens.getItensDeletados(venda.getUuid()));
		venda.calcularValorTotal();
		venda.calcularValorComissao();
		
		vendaValidator.validate(venda, result);
	}
	
	private void setUuid(Venda venda) {
		if (StringUtils.isEmpty(venda.getUuid())) {
			venda.setUuid(UUID.randomUUID().toString());
		}
	}
	
	private List<StatusVenda> findStatusVenda(){
		List<StatusVenda> statusVenda = new ArrayList<StatusVenda>();
		statusVenda.add(StatusVenda.FATURADA);
		statusVenda.add(StatusVenda.TRANSPORTE);
		return statusVenda;
	}
	
	private boolean verificarPermissao(UsuarioSistema usuarioSistema){
		return usuarioSistema.getAuthorities().stream()
				.anyMatch(i -> i.getAuthority().equals("ROLE_TODAS_VENDAS"));
	}

}
