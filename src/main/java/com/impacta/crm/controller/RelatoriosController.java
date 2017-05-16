package com.impacta.crm.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.impacta.crm.dto.FiltroRelatorioComissaoVendedor;
import com.impacta.crm.dto.PeriodoRelatorio;
import com.impacta.crm.model.Cliente;
import com.impacta.crm.model.StatusVenda;
import com.impacta.crm.model.Usuario;
import com.impacta.crm.model.Venda;

@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {
	
	@GetMapping("/vendasEmitidas")
	public ModelAndView relatorioVendasEmitidas() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioVendasEmitidas");
		mv.addObject(new PeriodoRelatorio());
		return mv;
	}
	
	@PostMapping("/vendasEmitidas")
	public ModelAndView gerarRelatorioVendasEmitidas(PeriodoRelatorio periodoRelatorio) {
		Map<String, Object> parametros = new HashMap<>();
		
		Date dataInicio = Date.from(LocalDateTime.of(periodoRelatorio.getDataInicio(), LocalTime.of(0, 0, 0))
				.atZone(ZoneId.systemDefault()).toInstant());
		Date dataFim = Date.from(LocalDateTime.of(periodoRelatorio.getDataFim(), LocalTime.of(23, 59, 59))
				.atZone(ZoneId.systemDefault()).toInstant());
		
		parametros.put("format", "pdf");
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFim);
		
		return new ModelAndView("relatorio_vendas_emitidas", parametros);
	}
	
	@GetMapping("/comissaoVendedor")
	public ModelAndView relatorioComissaoVendedor() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioComissaoVendedor");
		mv.addObject(new FiltroRelatorioComissaoVendedor());
		return mv;
	}
	
	@PostMapping("/comissaoVendedor")
	public ModelAndView gerarRelatorioComissaoVendedor(FiltroRelatorioComissaoVendedor filtroRelatorioComissao) {
		Map<String, Object> parametros = new HashMap<>();
		
		Date dataInicio = Date.from(LocalDateTime.of(filtroRelatorioComissao.getPeriodoRelatorio().getDataInicio()
				, LocalTime.of(0, 0, 0)).atZone(ZoneId.systemDefault()).toInstant());
		Date dataFim = Date.from(LocalDateTime.of(filtroRelatorioComissao.getPeriodoRelatorio().getDataFim()
				, LocalTime.of(23, 59, 59)).atZone(ZoneId.systemDefault()).toInstant());
		
		parametros.put("format", "pdf");
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_final", dataFim);
		parametros.put("codigo_usuario", filtroRelatorioComissao.getCodigoVendedor());
		
		return new ModelAndView("relatorio_comissao_vendedor", parametros);
	}
	
}
