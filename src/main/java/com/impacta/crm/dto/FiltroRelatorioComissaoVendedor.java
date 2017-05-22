package com.impacta.crm.dto;

import java.time.LocalDate;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;



public class FiltroRelatorioComissaoVendedor {
	
	@NotNull(message = "Vendedor é obrigatório")
	private Long codigoVendedor;
	
	@NotNull(message = "Data inicio é obrigatório")
	private LocalDate dataInicio;
	
	@NotNull(message = "Data fim é obrigatório")
	private LocalDate dataFim;
	
	@AssertTrue(message="Data inicio deve ser menor ou igual a data final")
	private boolean isValidRange() {
		return this.dataInicio == null ? true : this.dataInicio.isBefore(this.dataFim) || this.dataInicio.isEqual(this.dataFim);
	}
	
	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	
	public Long getCodigoVendedor() {
		return codigoVendedor;
	}
	
	public void setCodigoVendedor(Long codigoVendedor) {
		this.codigoVendedor = codigoVendedor;
	}
}
