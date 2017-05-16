package com.impacta.crm.dto;

public class FiltroRelatorioComissaoVendedor {
	private PeriodoRelatorio periodoRelatorio;
	private Long codigoVendedor;
	
	public FiltroRelatorioComissaoVendedor(){
		periodoRelatorio = new PeriodoRelatorio();
	}
	public PeriodoRelatorio getPeriodoRelatorio() {
		return periodoRelatorio;
	}
	public void setPeriodoRelatorio(PeriodoRelatorio periodoRelatorio) {
		this.periodoRelatorio = periodoRelatorio;
	}
	public Long getCodigoVendedor() {
		return codigoVendedor;
	}
	public void setCodigoVendedor(Long codigoVendedor) {
		this.codigoVendedor = codigoVendedor;
	}
}
