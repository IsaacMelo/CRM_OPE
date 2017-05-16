package com.impacta.crm.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

public class PeriodoRelatorio {
	
	@NotNull(message = "Data inicio é obrigatório")
	private LocalDate dataInicio;
	
	@NotNull(message = "Data fim é obrigatório")
	private LocalDate dataFim;

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

}
