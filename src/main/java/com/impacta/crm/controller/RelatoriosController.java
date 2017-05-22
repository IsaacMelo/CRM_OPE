package com.impacta.crm.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.impacta.crm.dto.FiltroRelatorioComissaoVendedor;
import com.impacta.crm.dto.FiltroRelatorioProduto;
import com.impacta.crm.dto.PeriodoRelatorio;
import com.impacta.crm.dto.TipoRelatorioProduto;

@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {
	
	@GetMapping("/vendasEmitidas")
	public ModelAndView relatorioVendasEmitidas(PeriodoRelatorio periodoRelatorio) {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioVendasEmitidas");
		mv.addObject(periodoRelatorio);
		return mv;
	}
	
	@PostMapping("/vendasEmitidas")
	public ModelAndView gerarRelatorioVendasEmitidas(@Valid PeriodoRelatorio periodoRelatorio, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return relatorioVendasEmitidas(periodoRelatorio);
		}
		
		Map<String, Object> parametros = new HashMap<>();
		
		Date dataInicio = Date.from(LocalDateTime.of(periodoRelatorio.getDataInicio(), LocalTime.of(0, 0, 0))
				.atZone(ZoneId.systemDefault()).toInstant());
		Date dataFim = Date.from(LocalDateTime.of(periodoRelatorio.getDataFim(), LocalTime.of(23, 59, 59))
				.atZone(ZoneId.systemDefault()).toInstant());
		
		parametros.put("format", "pdf");
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFim);
		parametros.put("sub_report_page_footer", "relatorios/sub-relatorios/relatorio_page_footer.jasper");
		
		return new ModelAndView("relatorio_vendas_emitidas", parametros);
	}
	
	@GetMapping("/comissaoVendedor")
	public ModelAndView relatorioComissaoVendedor(FiltroRelatorioComissaoVendedor filtroRelatorioComissao) {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioComissaoVendedor");
		mv.addObject(filtroRelatorioComissao);
		return mv;
	}
	
	@RequestMapping(value = "/comissaoVendedor", method = RequestMethod.POST)
	public ModelAndView gerarRelatorioComissaoVendedor(@Valid FiltroRelatorioComissaoVendedor filtroRelatorioComissao, 
			BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return relatorioComissaoVendedor(filtroRelatorioComissao);
		}
		
		Map<String, Object> parametros = new HashMap<>();
		
		Date dataInicio = Date.from(LocalDateTime.of(filtroRelatorioComissao.getDataInicio()
				, LocalTime.of(0, 0, 0)).atZone(ZoneId.systemDefault()).toInstant());
		Date dataFim = (Date) Date.from(LocalDateTime.of(filtroRelatorioComissao.getDataFim()
				, LocalTime.of(23, 59, 59)).atZone(ZoneId.systemDefault()).toInstant());
		
		parametros.put("format", "pdf");
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_final", dataFim);
		parametros.put("codigo_usuario", filtroRelatorioComissao.getCodigoVendedor());
		parametros.put("sub_report_page_footer", "relatorios/sub-relatorios/relatorio_page_footer.jasper");
		
		return new ModelAndView("relatorio_comissao_vendedor", parametros);

	}
	
	@GetMapping("/produtos")
	public ModelAndView relatorioProdutos(FiltroRelatorioProduto filtroRelatorioProduto) {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioProdutos");
		mv.addObject("todosRelatorios", TipoRelatorioProduto.values());
		mv.addObject(filtroRelatorioProduto);
		return mv;
	}
	
	@RequestMapping(value = "/produtos", method = RequestMethod.POST)
	public ModelAndView relatorioProdutos(FiltroRelatorioProduto filtroRelatorioProduto, 
			BindingResult result, RedirectAttributes attributes) {

		Map<String, Object> parametros = new HashMap<>();

		parametros.put("format", "pdf");
		parametros.put("sub_report_page_footer", "relatorios/sub-relatorios/relatorio_page_footer.jasper");
		
		if (filtroRelatorioProduto.getRelatorio() == TipoRelatorioProduto.ATIVO)
			return new ModelAndView("relatorio_produtos_ativo", parametros);
		if (filtroRelatorioProduto.getRelatorio() == TipoRelatorioProduto.INATIVO)
			return new ModelAndView("relatorio_produtos_inativo", parametros);
		if (filtroRelatorioProduto.getRelatorio() == TipoRelatorioProduto.NEGATIVO)
			return new ModelAndView("relatorio_produtos_negativos", parametros);

		return new ModelAndView("relatorio_produtos", parametros);
	}
	
	@GetMapping("/vendasFinalizadas")
	public ModelAndView relatorioVendasFinalizadas(PeriodoRelatorio periodoRelatorio) {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioVendasFinalizadas");
		mv.addObject(periodoRelatorio);
		return mv;
	}
	
	@PostMapping("/vendasFinalizadas")
	public ModelAndView gerarRelatorioVendasFinalizadas(@Valid PeriodoRelatorio periodoRelatorio, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return relatorioVendasEmitidas(periodoRelatorio);
		}
		
		Map<String, Object> parametros = new HashMap<>();
		
		Date dataInicio = Date.from(LocalDateTime.of(periodoRelatorio.getDataInicio(), LocalTime.of(0, 0, 0))
				.atZone(ZoneId.systemDefault()).toInstant());
		Date dataFim = Date.from(LocalDateTime.of(periodoRelatorio.getDataFim(), LocalTime.of(23, 59, 59))
				.atZone(ZoneId.systemDefault()).toInstant());
		
		parametros.put("format", "pdf");
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFim);
		parametros.put("sub_report_page_footer", "relatorios/sub-relatorios/relatorio_page_footer.jasper");
		
		return new ModelAndView("relatorio_vendas_finalizadas", parametros);
	}
}
