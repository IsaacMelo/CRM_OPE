package com.impacta.crm.dto;

import javax.persistence.Embedded;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;



public class FiltroRelatorioComissaoVendedor {
	@Valid
	@Embedded
	private PeriodoRelatorio periodoRelatorio;
	@NotNull(message = "Vendedor é obrigatório")
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
